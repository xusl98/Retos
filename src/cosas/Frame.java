package cosas;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author daw120
 */
public class Frame extends javax.swing.JFrame {

    TreeSet<RankingCoches> ranking = new TreeSet<>();

    ArrayList<JLabel> respuestas = new ArrayList<>();

    Timer timer;

    static int movimiento = 0;

    JLabel background;

    boolean salido = false;

    int respCorrecta = -1;
    int respElegida = -1;

    int cocheX = 38;
    int cocheY = 47;
    int cocheHorizontalX = 90;
    int cocheHorizontalY = 53;

    int rondas = 0;
    int maxRondas = 5;

    int aciertos = 0;
    int fallos = 0;

    JFrame thisFrame = this;

    ArrayList<Integer> movimientos = new ArrayList<>();

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
        setLocationRelativeTo(this);
        setResizable(false);

        respuestas.add(lblResp1);
        respuestas.add(lblResp2);
        respuestas.add(lblResp3);
        respuestas.add(lblResp4);

        pregunta();

        lblCoche.setSize(cocheX, cocheY);

        lblCoche.setLocation(321, 237);

        setTitle("Cálculo");

        lblNombre.setText(Menu.nombre);
        lblNombre.setForeground(Color.white);

        lblPregunta.setBackground(Color.white);
        lblPregunta.setOpaque(true);

        lblResp1.setBackground(Color.white);
        lblResp1.setOpaque(true);
        lblResp2.setBackground(Color.white);
        lblResp2.setOpaque(true);
        lblResp3.setBackground(Color.white);
        lblResp3.setOpaque(true);
        lblResp4.setBackground(Color.white);
        lblResp4.setOpaque(true);

        BufferedImage imgCoche = null;
        BufferedImage imgFondo = null;
        try {
            imgCoche = ImageIO.read(new File("coche.png"));
            imgFondo = ImageIO.read(new File("fondo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimgCoche = imgCoche.getScaledInstance(lblCoche.getWidth(), lblCoche.getHeight(),
                Image.SCALE_SMOOTH);
        Image dimgFondo = imgFondo.getScaledInstance(this.getWidth(), this.getHeight(),
                Image.SCALE_SMOOTH);
        lblCoche.setIcon(new ImageIcon(dimgCoche));

        jPanel1.setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon(dimgFondo));
        jPanel1.add(background);
        background.setLayout(new FlowLayout());

        movimientos.add(0);

        this.addKeyListener(new MyKeyListener());

        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int x = lblCoche.getX();
                int y = lblCoche.getY();
//                System.out.println("x" + lblCoche.getX());
//                System.out.println(lblCoche.getY());

                //ARRIBA
                if (movimientos.get(movimientos.size() - 1) == 1) {

                    lblCoche.setSize(cocheX, cocheY);
                    BufferedImage imgCoche = null;
                    try {
                        imgCoche = ImageIO.read(new File("coche.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image dimgCoche = imgCoche.getScaledInstance(lblCoche.getWidth(), lblCoche.getHeight(),
                            Image.SCALE_SMOOTH);
                    lblCoche.setIcon(new ImageIcon(dimgCoche));

                    lblCoche.setLocation(x, y);
                    if ((lblCoche.getX() > 361 && lblCoche.getY() < 228) || (lblCoche.getX() < 281 && lblCoche.getY() < 228)) {

                    } else {
                        lblCoche.setLocation(lblCoche.getX(), lblCoche.getY() - 20);
                    }

                }
                //DERECHA
                if (movimientos.get(movimientos.size() - 1) == 2) {

                    lblCoche.setSize(cocheHorizontalX, cocheHorizontalY);
                    BufferedImage imgCoche = null;
                    try {
                        imgCoche = ImageIO.read(new File("cocheDerecha.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image dimgCoche = imgCoche.getScaledInstance(lblCoche.getWidth(), lblCoche.getHeight(),
                            Image.SCALE_SMOOTH);
                    lblCoche.setIcon(new ImageIcon(dimgCoche));

                    lblCoche.setLocation(x, y);
                    if ((lblCoche.getX() > 341 && lblCoche.getY() < 207) || (lblCoche.getX() > 341 && lblCoche.getY() > 277)) {

                    } else {
                        lblCoche.setLocation(lblCoche.getX() + 20, lblCoche.getY());
                    }
                }
                //ABAJO
                if (movimientos.get(movimientos.size() - 1) == 3) {
                    lblCoche.setSize(cocheX, cocheY);
                    BufferedImage imgCoche = null;
                    try {
                        imgCoche = ImageIO.read(new File("cocheAbajo.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image dimgCoche = imgCoche.getScaledInstance(lblCoche.getWidth(), lblCoche.getHeight(),
                            Image.SCALE_SMOOTH);
                    lblCoche.setIcon(new ImageIcon(dimgCoche));

                    lblCoche.setLocation(x, y);
                    if ((lblCoche.getX() > 341 && lblCoche.getY() > 276) || (lblCoche.getX() < 281 && lblCoche.getY() > 276)) {

                    } else {
                        lblCoche.setLocation(lblCoche.getX(), lblCoche.getY() + 20);
                    }
                }
                //IZQUIERDA
                if (movimientos.get(movimientos.size() - 1) == 4) {

                    lblCoche.setSize(cocheHorizontalX, cocheHorizontalY);
                    BufferedImage imgCoche = null;
                    try {
                        imgCoche = ImageIO.read(new File("cocheIzquierda.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image dimgCoche = imgCoche.getScaledInstance(lblCoche.getWidth(), lblCoche.getHeight(),
                            Image.SCALE_SMOOTH);
                    lblCoche.setIcon(new ImageIcon(dimgCoche));

                    lblCoche.setLocation(x, y);
                    if ((lblCoche.getX() < 282 && lblCoche.getY() < 207) || (lblCoche.getX() < 282 && lblCoche.getY() > 277)) {

                    } else {
                        lblCoche.setLocation(lblCoche.getX() - 20, lblCoche.getY());
                    }
                }

                movimientos.add(0);

                //LLEGADA A RESPUESTAS
                if (lblCoche.getY() < 20) {
                    respElegida = 1;
                    if (!salido) {
                        salido = true;
                        if (respElegida == respCorrecta) {
                            JOptionPane.showMessageDialog(null, "Correcto");
                            aciertos++;
                            rondas++;
                            lblAciertos.setText("Aciertos = " + aciertos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, la respuesta correcta era: " + respuestas.get(respCorrecta).getText());
                            fallos++;
                            rondas++;
                            lblFallos.setText("Fallos = " + fallos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        }
                    }
                }
                if (lblCoche.getY() > 440) {
                    respElegida = 2;
                    if (!salido) {
                        salido = true;
                        if (respElegida == respCorrecta) {
                            JOptionPane.showMessageDialog(null, "Correcto");
                            aciertos++;
                            rondas++;
                            lblAciertos.setText("Aciertos = " + aciertos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, la respuesta correcta era: " + respuestas.get(respCorrecta).getText());
                            fallos++;
                            lblFallos.setText("Fallos = " + fallos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        }
                    }
                }
                if (lblCoche.getX() > 583) {
                    respElegida = 0;
                    if (!salido) {
                        salido = true;
                        if (respElegida == respCorrecta) {
                            JOptionPane.showMessageDialog(null, "Correcto");
                            aciertos++;
                            rondas++;
                            lblAciertos.setText("Aciertos = " + aciertos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, la respuesta correcta era: " + respuestas.get(respCorrecta).getText());
                            fallos++;
                            lblFallos.setText("Fallos = " + fallos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        }
                    }
                }
                if (lblCoche.getX() < 0) {
                    respElegida = 3;
                    if (!salido) {
                        salido = true;
                        if (respElegida == respCorrecta) {
                            JOptionPane.showMessageDialog(null, "Correcto");
                            aciertos++;
                            rondas++;
                            lblAciertos.setText("Aciertos = " + aciertos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrecto, la respuesta correcta era: " + respuestas.get(respCorrecta).getText());
                            fallos++;
                            rondas++;
                            lblFallos.setText("Fallos = " + fallos);
                            lblCoche.setLocation(321, 237);
                            salido = false;
                            pregunta();
                        }
                    }
                }

                //AL LLEGAR AL MÁXIMO DE RONDAS
                if (rondas == maxRondas) {
                    timer.stop();
                    jPanel1.setVisible(false);
                    jPanel2.setVisible(true);

                    File archivo = new File("ranking.dat");
                    if (archivo.exists()) {
                        ObjectInputStream os = null;
                        try {
                            os = new ObjectInputStream(new FileInputStream(archivo));
                            RankingCoches r;
                            while (true){
                                r = (RankingCoches) os.readObject();
                                ranking.add(r);
                            }
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        } finally {
                            if (os != null){
                                try {
                                    os.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    ranking.add(new RankingCoches(Menu.nombre, aciertos, fallos));
                    
                    
                    ArrayList<RankingCoches> array = new ArrayList<>();
                    for (RankingCoches rr : ranking){
                        array.add(rr);
                        System.out.println(rr.getNombre() + rr.getAciertos());
                    }

                    BufferedImage imgRanking = null;
                    try {
                        imgRanking = ImageIO.read(new File("puntos.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image dimgRanking = imgRanking.getScaledInstance(lblRanking.getWidth(), lblRanking.getHeight(),
                            Image.SCALE_SMOOTH);
                    lblRanking.setIcon(new ImageIcon(dimgRanking));
                    lblRanking2.setIcon(new ImageIcon(dimgRanking));
                    lblRanking3.setIcon(new ImageIcon(dimgRanking));
                    lblRanking4.setIcon(new ImageIcon(dimgRanking));
                    lblRanking5.setIcon(new ImageIcon(dimgRanking));

                    try {
                        lblRank1.setText(array.get(array.size()-1).getNombre() + "        Aciertos: " + array.get(array.size()-1).getAciertos() + "        Fallos: " + array.get(array.size()-1).getFallos());
                        lblRank2.setText(array.get(array.size()-2).getNombre() + "        Aciertos: " + array.get(array.size()-2).getAciertos() + "        Fallos: " + array.get(array.size()-2).getFallos());
                        lblRank3.setText(array.get(array.size()-3).getNombre() + "        Aciertos: " + array.get(array.size()-3).getAciertos() + "        Fallos: " + array.get(array.size()-3).getFallos());
                        lblRank4.setText(array.get(array.size()-4).getNombre() + "        Aciertos: " + array.get(array.size()-4).getAciertos() + "        Fallos: " + array.get(array.size()-4).getFallos());
                        lblRank5.setText(array.get(array.size()-5).getNombre() + "        Aciertos: " + array.get(array.size()-5).getAciertos() + "        Fallos: " + array.get(array.size()-5).getFallos());

                    } catch (Exception e) {

                    }

                    ObjectOutputStream os = null;
                    try {
                        os = new ObjectOutputStream(new FileOutputStream(archivo));
                        for (RankingCoches r : ranking) {
                            os.writeObject(r);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        if (os != null){
                            try {
                                os.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }

            }
        });
        timer.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCoche = new javax.swing.JLabel();
        lblResp1 = new javax.swing.JLabel();
        lblResp2 = new javax.swing.JLabel();
        lblResp3 = new javax.swing.JLabel();
        lblResp4 = new javax.swing.JLabel();
        lblPregunta = new javax.swing.JLabel();
        lblAciertos = new javax.swing.JLabel();
        lblFallos = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelRanking = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblRank1 = new javax.swing.JLabel();
        lblRanking = new javax.swing.JLabel();
        panelRanking2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblRank2 = new javax.swing.JLabel();
        lblRanking2 = new javax.swing.JLabel();
        panelRanking3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblRank3 = new javax.swing.JLabel();
        lblRanking3 = new javax.swing.JLabel();
        panelRanking4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblRank4 = new javax.swing.JLabel();
        lblRanking4 = new javax.swing.JLabel();
        panelRanking5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblRank5 = new javax.swing.JLabel();
        lblRanking5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        lblResp1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResp1.setText("jLabel2");

        lblResp2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResp2.setText("jLabel2");

        lblResp3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResp3.setText("jLabel2");

        lblResp4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResp4.setText("jLabel2");

        lblPregunta.setBackground(new java.awt.Color(255, 255, 255));
        lblPregunta.setText("jLabel2");
        lblPregunta.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblAciertos.setBackground(new java.awt.Color(255, 255, 255));
        lblAciertos.setForeground(new java.awt.Color(255, 255, 255));
        lblAciertos.setText("Aciertos = 0");
        lblAciertos.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblFallos.setBackground(new java.awt.Color(255, 255, 255));
        lblFallos.setForeground(new java.awt.Color(255, 255, 255));
        lblFallos.setText("Fallos = 0");
        lblFallos.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblNombre.setBackground(new java.awt.Color(255, 255, 255));
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre");
        lblNombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFallos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(lblCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 293, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblResp4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblResp1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lblResp2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(261, 261, 261))
                        .addComponent(lblResp3))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFallos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(lblCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 170, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblResp2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(148, 148, 148)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblResp1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblResp4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                    .addComponent(lblResp3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        getContentPane().add(jPanel1, "card2");

        panelRanking.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("1");
        panelRanking.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        lblRank1.setForeground(new java.awt.Color(255, 255, 255));
        panelRanking.add(lblRank1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 450, 20));
        panelRanking.add(lblRanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 666, 20));

        panelRanking2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("2");
        panelRanking2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        lblRank2.setForeground(new java.awt.Color(255, 255, 255));
        panelRanking2.add(lblRank2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 450, 20));
        panelRanking2.add(lblRanking2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 666, 20));

        panelRanking3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("3");
        panelRanking3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        lblRank3.setForeground(new java.awt.Color(255, 255, 255));
        panelRanking3.add(lblRank3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 450, 20));
        panelRanking3.add(lblRanking3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 666, 20));

        panelRanking4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("4");
        panelRanking4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        lblRank4.setForeground(new java.awt.Color(255, 255, 255));
        panelRanking4.add(lblRank4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 450, 20));
        panelRanking4.add(lblRanking4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 666, 20));

        panelRanking5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("5");
        panelRanking5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        lblRank5.setForeground(new java.awt.Color(255, 255, 255));
        panelRanking5.add(lblRank5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 450, 20));
        panelRanking5.add(lblRanking5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 666, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRanking, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRanking2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRanking3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRanking4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRanking5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(panelRanking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRanking2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRanking3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRanking4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRanking5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card3");

        jMenu1.setText("Finalizar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        Menu.nombrePreguntado = true;
        this.dispose();
        Menu frame = new Menu();
        frame.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    class MyKeyListener extends KeyAdapter {

        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyCode() == 38) {
                movimientos.add(1);
            }
            if (evt.getKeyCode() == 39) {
                movimientos.add(2);
            }
            if (evt.getKeyCode() == 40) {
                movimientos.add(3);
            }
            if (evt.getKeyCode() == 37) {
                movimientos.add(4);
            }
        }
    }

    public void pregunta() {
        int x = (int) Math.floor(Math.random() * 9) + 1;
        int y = (int) Math.floor(Math.random() * 9) + 1;

        lblPregunta.setText("  " + x + "x" + y + " = ?");

        int respuesta = x * y;
        respuestas(respuesta);

    }

    public void respuestas(int respuesta) {

        int x = (int) Math.floor(Math.random() * 3) + 1;

        for (int i = 0; i < respuestas.size(); i++) {
            if (i != x) {
                int y = (int) Math.floor(Math.random() * 100) + 1;
                respuestas.get(i).setText(String.valueOf(y));
            } else {
                respCorrecta = i;
                respuestas.get(i).setText(String.valueOf(respuesta));
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAciertos;
    private javax.swing.JLabel lblCoche;
    private javax.swing.JLabel lblFallos;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblRank1;
    private javax.swing.JLabel lblRank2;
    private javax.swing.JLabel lblRank3;
    private javax.swing.JLabel lblRank4;
    private javax.swing.JLabel lblRank5;
    private javax.swing.JLabel lblRanking;
    private javax.swing.JLabel lblRanking2;
    private javax.swing.JLabel lblRanking3;
    private javax.swing.JLabel lblRanking4;
    private javax.swing.JLabel lblRanking5;
    private javax.swing.JLabel lblResp1;
    private javax.swing.JLabel lblResp2;
    private javax.swing.JLabel lblResp3;
    private javax.swing.JLabel lblResp4;
    private javax.swing.JPanel panelRanking;
    private javax.swing.JPanel panelRanking2;
    private javax.swing.JPanel panelRanking3;
    private javax.swing.JPanel panelRanking4;
    private javax.swing.JPanel panelRanking5;
    // End of variables declaration//GEN-END:variables
}
