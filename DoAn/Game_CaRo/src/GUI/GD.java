/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Manager.Manager;
import Manager.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Black Dung
 */
public class GD extends javax.swing.JFrame {

    /**
     * Creates new form GD
     */
    private Manager manager;
    public static Timer t;
    private Timer tBao;
    public static boolean checkTime = false; // true là hết giờ
    private ActionListener action;

    public GD() {
        initComponents();
        rd_PVsP.setEnabled(false);
        rd_PVsP.setSelected(true);
        // ẩn thanh probar + lable
        lbl_mess.setVisible(false);
        pro_Bar.setVisible(false);
        settingALL();
        // Manager
        manager = new Manager(BanCo, txt_name1, txt_name2, lbl_avtPlayer, lbl_mess, pro_Bar);
        manager.TaoBanCo();
        // them anh
        addImage();
        Timer();
        // Thanh timer thông báo
        thongbaoDS();
        btn_Pause.setEnabled(false);
    }

    public void settingALL() // thay đổi màu sắc khung, cỡ kiểu màu chữ
    {
        // thay đổi con trỏ chuột
        ImageIcon chuot = new ImageIcon(new ImageIcon(getClass().getResource("/Image/chuot.png")).getImage().getScaledInstance(260, 100, java.awt.Image.SCALE_DEFAULT));
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(chuot.getImage(), new Point(0, 30), "chuột game"));
        //nút start
        ImageIcon start = new ImageIcon(new ImageIcon(getClass().getResource("/Image/start.png")).getImage().getScaledInstance(108, 45, java.awt.Image.SCALE_DEFAULT));
        btn_Start.setIcon(start);
        btn_Start.setToolTipText("Nhấn để bắt đầu game");
        btn_Start.setContentAreaFilled(false);
        btn_Start.setText("");
        btn_Start.setBorderPainted(false);
        //nút help
        btn_Help.setToolTipText("Nhấn để xem luật chơi");
        btn_Help.setContentAreaFilled(false);
        btn_Help.setBorderPainted(false);
        ImageIcon help = new ImageIcon(new ImageIcon(getClass().getResource("/Image/help.png")).getImage().getScaledInstance(150, 60, java.awt.Image.SCALE_DEFAULT));
        btn_Help.setIcon(help);
        // nút exit
        btn_Exit.setToolTipText("Nhấn để thoát");
        btn_Exit.setContentAreaFilled(false);
        btn_Exit.setBorderPainted(false);
        ImageIcon exit = new ImageIcon(new ImageIcon(getClass().getResource("/Image/exit.png")).getImage().getScaledInstance(170, 60, java.awt.Image.SCALE_DEFAULT));
        btn_Exit.setIcon(exit);
        // nút reset player
        btn_resetName.setForeground(Color.RED);
        btn_resetName.setContentAreaFilled(false);
        btn_resetName.setToolTipText("Thay đổi tên người chơi");
         // nút tạm dừng
        btn_Pause.setText("");
        ImageIcon pause = new ImageIcon(new ImageIcon(getClass().getResource("/Image/help1.png")).getImage().getScaledInstance(170, 60, java.awt.Image.SCALE_DEFAULT));
        btn_Pause.setBorderPainted(false);
        btn_Pause.setIcon(pause);
        btn_Pause.setContentAreaFilled(false);
        btn_Pause.setToolTipText("Dừng game");
        // nút undo
        btn_Undo.setBorderPainted(false);
        btn_Undo.setContentAreaFilled(false);
        btn_Undo.setToolTipText("Hủy bỏ ( trở về quá khứ 1 bước ) (Ctrl Z))");
        ImageIcon undo = new ImageIcon(new ImageIcon(getClass().getResource("/Image/undo.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
        btn_Undo.setIcon(undo);
        // nút redo
        btn_Redo.setBorderPainted(false);
        btn_Redo.setContentAreaFilled(false);
        btn_Redo.setToolTipText("Làm lại ( Đi đến tương lai 1 bước ) (Ctrl Y))");
        ImageIcon redo = new ImageIcon(new ImageIcon(getClass().getResource("/Image/redo.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
        btn_Redo.setIcon(redo);
        // fix khung
        setSize(1111, 665);
        setResizable(false);
        setLocation(150, 35);
        setBackground(Color.yellow);
        setForeground(Color.red);
        lbl_mess.setForeground(Color.darkGray);
    }

    public void addImage() {
        // add avatar game
        ImageIcon imageLeftTop = new ImageIcon(new ImageIcon(getClass().getResource("/Image/hinh1.png")).getImage().getScaledInstance(228, 224, Image.SCALE_DEFAULT));
        lblavt_Game.setIcon(imageLeftTop);
        // add avt người chơi khi khởi động vào
        ImageIcon a = new ImageIcon(new ImageIcon(getClass().getResource("/Image/avtx.png")).getImage().getScaledInstance(247, 236, java.awt.Image.SCALE_DEFAULT));
        lbl_avtPlayer.setIcon(a);
        //
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblavt_Game = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_avtPlayer = new javax.swing.JLabel();
        BanCo = new javax.swing.JPanel();
        pnl_thongbao = new javax.swing.JPanel();
        lbl_thongbao = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_name1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_name2 = new javax.swing.JTextField();
        btn_Start = new javax.swing.JButton();
        pro_Bar = new javax.swing.JProgressBar();
        lbl_mess = new javax.swing.JLabel();
        btn_resetName = new javax.swing.JButton();
        rd_ChanHaiDau = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        btn_Undo = new javax.swing.JButton();
        btn_Redo = new javax.swing.JButton();
        btn_Exit = new javax.swing.JButton();
        btn_Help = new javax.swing.JButton();
        btn_Pause = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        rd_PVsP = new javax.swing.JCheckBox();
        rd_PVC = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem_PlayerVsPlayer = new javax.swing.JMenuItem();
        jMenuItem_PlayerVsCom = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btn_Quit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menu_Undo = new javax.swing.JMenuItem();
        menu_Redo = new javax.swing.JMenuItem();
        jMenu_Help = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(226, 226));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblavt_Game, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblavt_Game, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(224, 224));

        lbl_avtPlayer.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_avtPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_avtPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );

        BanCo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BanCo.setPreferredSize(new java.awt.Dimension(600, 600));
        BanCo.setRequestFocusEnabled(false);

        javax.swing.GroupLayout BanCoLayout = new javax.swing.GroupLayout(BanCo);
        BanCo.setLayout(BanCoLayout);
        BanCoLayout.setHorizontalGroup(
            BanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        BanCoLayout.setVerticalGroup(
            BanCoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        pnl_thongbao.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnl_thongbao.setPreferredSize(new java.awt.Dimension(226, 204));

        lbl_thongbao.setText("jLabel4");

        javax.swing.GroupLayout pnl_thongbaoLayout = new javax.swing.GroupLayout(pnl_thongbao);
        pnl_thongbao.setLayout(pnl_thongbaoLayout);
        pnl_thongbaoLayout.setHorizontalGroup(
            pnl_thongbaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_thongbaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_thongbao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_thongbaoLayout.setVerticalGroup(
            pnl_thongbaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_thongbaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_thongbao, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setPreferredSize(new java.awt.Dimension(226, 204));

        txt_name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_name1MouseClicked(evt);
            }
        });

        jLabel1.setText("Player1");

        jLabel2.setText("Player2");

        txt_name2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_name2MouseClicked(evt);
            }
        });

        btn_Start.setText("StartGame");
        btn_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StartActionPerformed(evt);
            }
        });

        pro_Bar.setForeground(new java.awt.Color(255, 0, 51));

        lbl_mess.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_mess.setText("            X đi trước");

        btn_resetName.setText("Rename");
        btn_resetName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetNameActionPerformed(evt);
            }
        });

        rd_ChanHaiDau.setText("Chặn 2 đầu");
        rd_ChanHaiDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_ChanHaiDauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pro_Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_name1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_mess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_resetName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rd_ChanHaiDau)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_resetName)
                    .addComponent(rd_ChanHaiDau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_mess)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pro_Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Start)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btn_Undo.setText("Undo");
        btn_Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UndoActionPerformed(evt);
            }
        });

        btn_Redo.setText("Redo");
        btn_Redo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_Redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RedoActionPerformed(evt);
            }
        });

        btn_Exit.setPreferredSize(new java.awt.Dimension(230, 80));
        btn_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExitActionPerformed(evt);
            }
        });

        btn_Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HelpActionPerformed(evt);
            }
        });

        btn_Pause.setText("Pause");
        btn_Pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PauseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(btn_Help, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btn_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btn_Undo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Redo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btn_Pause, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Undo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Redo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btn_Pause, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Help, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Exit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "KIỂU ĐẤU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        buttonGroup1.add(rd_PVsP);
        rd_PVsP.setText("Player Vs Player");
        rd_PVsP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_PVsPActionPerformed(evt);
            }
        });

        buttonGroup1.add(rd_PVC);
        rd_PVC.setText("Player Vs Com");
        rd_PVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_PVCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rd_PVsP)
                    .addComponent(rd_PVC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(rd_PVsP)
                .addGap(18, 18, 18)
                .addComponent(rd_PVC)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/mnNew.png"))); // NOI18N
        jMenu3.setText("New");

        jMenuItem_PlayerVsPlayer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_PlayerVsPlayer.setText("Player vs Player");
        jMenuItem_PlayerVsPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_PlayerVsPlayerActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_PlayerVsPlayer);

        jMenuItem_PlayerVsCom.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_PlayerVsCom.setText("Player vs Com");
        jMenuItem_PlayerVsCom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_PlayerVsComActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem_PlayerVsCom);

        jMenu1.add(jMenu3);
        jMenu1.add(jSeparator1);

        btn_Quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        btn_Quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/mnExit.png"))); // NOI18N
        btn_Quit.setText("Quit");
        btn_Quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_QuitActionPerformed(evt);
            }
        });
        jMenu1.add(btn_Quit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menu_Undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menu_Undo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/mnUndo.png"))); // NOI18N
        menu_Undo.setText("Undo");
        menu_Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_UndoActionPerformed(evt);
            }
        });
        jMenu2.add(menu_Undo);

        menu_Redo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        menu_Redo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/mnRedo.png"))); // NOI18N
        menu_Redo.setText("Redo");
        menu_Redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_RedoActionPerformed(evt);
            }
        });
        jMenu2.add(menu_Redo);

        jMenuBar1.add(jMenu2);

        jMenu_Help.setText("Help");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/mnAbout.png"))); // NOI18N
        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu_Help.add(jMenuItem1);

        jMenuBar1.add(jMenu_Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BanCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_thongbao, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_thongbao, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(BanCo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
                .addGap(26, 60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void Timer() {
        //pro_Bar.setValue(100);
        pro_Bar.setToolTipText("Thời gian suy nghĩ của mỗi lượt đánh");
        pro_Bar.setBackground(Color.white);
        pro_Bar.setForeground(Color.green);
        pro_Bar.setPreferredSize(new Dimension(200, 14));
        pro_Bar.setEnabled(false);
        pro_Bar.setBorderPainted(false);
        //  pro_Bar.setValue(100);
        action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pro_Bar.getValue() > 0) {
                    pro_Bar.setValue(pro_Bar.getValue() - 1);
                } else {
                    t.stop();
                    EndGameTheoThoiGian();
                }
            }
        };
        t = new Timer(150, action);
    }

    public void thongbaoDS() {
        lbl_thongbao.setText("Chọn kiểu đấu >> Nhập tên >> StartGame");
        lbl_thongbao.setForeground(Color.red);
        tBao = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl_thongbao.setLocation(lbl_thongbao.getLocation().x, lbl_thongbao.getLocation().y - 1);
                if (lbl_thongbao.getLocation().y + lbl_thongbao.getHeight() < 0) {
                    lbl_thongbao.setLocation(lbl_thongbao.getLocation().x, pnl_thongbao.getHeight());
                }
            }
        });
        tBao.start();
    }

    public void help() {
        String s = "";
        s = s + "1. Game có bàn cờ là 20x20 \n";
        s = s + "2. Game có 2 chế độ chơi:\n- Chơi với người. \n- Chơi với máy.\n";
        s = s + "3. Để bắt đầu game, trước hết bạn phải chọn chế độ chơi, nhập tên của người chơi sau đó nhất Start để bắt đầu chơi.\n";
        s = s + "4. Người nào tạo được 5 ô cùng hàng(chéo , ngang , dọc) và không bị người kia chặn 2 đầu thì sẽ thắng.\n";
        s = s + "5. Thời gian suy nghĩ là 10 giây cho mỗi người , hết thời gian suy nghĩ mà không đưa ra nước đi thì bị xử thua ván đó.\n";
        s = s + "6. Nhấn vào rename để thay đổi tên người chơi(áp dụng được khi game kết thúc).\n";
        s = s + "7. Game có tích hợp Undo ( Ctrl Z ) và Redo ( Ctrl Y ).\n";
        s = s + "8. Khi hoàn thành xong 1 game, nếu muốn tiếp tục chơi thì ấn Start để tiếp tục game mới(Nút start tích hợp luôn new game)..\n";
        s = s + "9. Một số phím tắt trong game:\n - Ctrl + Z : Undo.\n - Ctrl + Y : Redo.\n - Alt + F4 : Thoát game.\n ";
        JOptionPane.showConfirmDialog(null, s, "Help ?", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/Image/about.png")));

    }

    public void EndGameTheoThoiGian() {
        if (manager.getCurrentPlayer() == 0) {
            JOptionPane.showMessageDialog(rootPane, txt_name2.getText() + " đã giành chiến thắng vì " + txt_name1.getText() + " suy nghĩ quá lâu!!");
        } else {
            JOptionPane.showMessageDialog(rootPane, txt_name1.getText() + " đã giành chiến thắng vì " + txt_name2.getText() + " suy nghĩ quá lâu!!");
        }
        t.stop();
        manager.setSansang(false);
        // khoá tất cả nút undo redo khi thắng
        menu_Redo.setEnabled(false);
        menu_Undo.setEnabled(false);
        btn_Redo.setEnabled(false);
        btn_Undo.setEnabled(false);
        return;
    }
    public boolean KiemTra_Ten()
    {
        String str = "";
        int ktsl = 0;
        if (rd_PVsP.isSelected()) {
            if (txt_name1.getText().isEmpty()) {
                str = "Chưa nhập tên người chơi 1";
                ktsl++;
                txt_name1.setBackground(Color.red);
            }
        }
        if (txt_name2.getText().isEmpty()) {
           str ="Chưa nhập tên người chơi 2";
           ktsl++;
            txt_name2.setBackground(Color.red);
        }
        if(ktsl == 1)
        {
            JOptionPane.showMessageDialog(rootPane, str);
            return false;
        }
        else if(ktsl == 2)
             {
            JOptionPane.showMessageDialog(rootPane, "Chưa nhập tên người chơi 1 và người chơi 2");
            return false;
        }
        return true;
    }
    private void btn_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StartActionPerformed
        if(!KiemTra_Ten())
        {
            return;
        }
        btn_Pause.setEnabled(true);
        // hiện pro + lbl
        lbl_mess.setVisible(true);
        pro_Bar.setVisible(true);
        // sẵn sàng = true
        manager.setSansang(true);
        // mở tất cả undo, redo
        menu_Redo.setEnabled(true);
        menu_Undo.setEnabled(true);
        btn_Redo.setEnabled(true);
        btn_Undo.setEnabled(true);
        BanCo.removeAll();
        if (rd_PVsP.isSelected()) {
            manager.ChoiVoiNguoi();
        } else {
            manager.ChoiVoiMay();
        }
        // khoá 2 text name lại
        txt_name1.setRequestFocusEnabled(false);
        txt_name2.setRequestFocusEnabled(false);
        txt_name1.setBackground(Color.WHITE);
        txt_name2.setBackground(Color.WHITE);
        // chuẩn bị đánh
        t.stop();
        pro_Bar.setValue(100);
    }//GEN-LAST:event_btn_StartActionPerformed
    public void Undo() {
        manager.Undo(menu_Undo, btn_Undo); // người đánh
        if (rd_PVC.isSelected()) // dành cho chế độ với máy(máy đánh thì undo 2 lần)
        {
            manager.Undo(menu_Undo, btn_Undo);
        } 
        // khôi phục ngược về > khôi phục ngược lại
        menu_Redo.setEnabled(true);
        btn_Redo.setEnabled(true);
    }
    private void menu_UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_UndoActionPerformed
        Undo();
    }//GEN-LAST:event_menu_UndoActionPerformed
    public void Redo() {
        manager.Redo(menu_Redo, btn_Redo);
        if (rd_PVC.isSelected()) {
            manager.Redo(menu_Redo, btn_Redo);
        } 
        menu_Undo.setEnabled(true);
        btn_Undo.setEnabled(true);
    }
    private void menu_RedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_RedoActionPerformed
        Redo();
    }//GEN-LAST:event_menu_RedoActionPerformed

    public void PlayerVsPlayer() {
        lbl_mess.setText("");
        BanCo.removeAll();
        manager.TaoBanCo();
        rd_PVsP.setSelected(true);
        rd_PVsP.setEnabled(false);
        txt_name1.setText("");
        txt_name2.setText("");
        txt_name1.setRequestFocusEnabled(true);
        txt_name2.setRequestFocusEnabled(true);
        rd_PVC.setEnabled(true);
        txt_name1.setBackground(Color.WHITE);
        txt_name2.setBackground(Color.WHITE);
        manager.setSansang(false);
        menu_Undo.setEnabled(false); 
        menu_Redo.setEnabled(false);
        t.stop();
    }    public void PlayerVSCom() {
        lbl_mess.setText("");
        BanCo.removeAll();
        manager.TaoBanCo();
        rd_PVC.setSelected(true);
        rd_PVC.setEnabled(false);
        txt_name1.setRequestFocusEnabled(false);
        txt_name1.setText("I am bot");
        txt_name1.setBackground(Color.WHITE);
        txt_name2.setText("");
        rd_PVsP.setEnabled(true);
        txt_name2.setRequestFocusEnabled(true);
        txt_name2.setBackground(Color.WHITE);
        txt_name2.requestFocus();
        manager.setSansang(false);
        menu_Undo.setEnabled(false);
        menu_Redo.setEnabled(false);
        t.stop(); 
    }    public void QuitGame() {
        int hoi = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thoát Game ?", null, JOptionPane.YES_NO_OPTION);
        if (hoi == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
    private void btn_QuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuitActionPerformed
        QuitGame();
    }//GEN-LAST:event_btn_QuitActionPerformed

    private void jMenuItem_PlayerVsPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_PlayerVsPlayerActionPerformed
        PlayerVsPlayer();
    }//GEN-LAST:event_jMenuItem_PlayerVsPlayerActionPerformed

    private void jMenuItem_PlayerVsComActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_PlayerVsComActionPerformed
        PlayerVSCom();
    }//GEN-LAST:event_jMenuItem_PlayerVsComActionPerformed

    private void btn_resetNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetNameActionPerformed
        // Thay đổi tên người chơi
        if (manager.isSansang() == false) {
            txt_name1.setBackground(Color.WHITE);
            txt_name2.setBackground(Color.WHITE);
            if (rd_PVsP.isSelected()) {
                txt_name1.setRequestFocusEnabled(true);
                txt_name1.setText("");
            }
            txt_name2.setRequestFocusEnabled(true);
            txt_name2.setText("");
        }
    }//GEN-LAST:event_btn_resetNameActionPerformed

    private void btn_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExitActionPerformed
        QuitGame();
    }//GEN-LAST:event_btn_ExitActionPerformed

    private void btn_RedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RedoActionPerformed
        Redo();
    }//GEN-LAST:event_btn_RedoActionPerformed

    private void btn_UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UndoActionPerformed
        Undo();
    }//GEN-LAST:event_btn_UndoActionPerformed

    private void btn_HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HelpActionPerformed
        if (manager.isSansang() == false) {
            help();
        } else {
            t.stop();
            help();
            t.start();
        }
    }//GEN-LAST:event_btn_HelpActionPerformed

    private void txt_name1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_name1MouseClicked
        txt_name1.setBackground(Color.white);
    }//GEN-LAST:event_txt_name1MouseClicked

    private void txt_name2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_name2MouseClicked
        txt_name2.setBackground(Color.white);
    }//GEN-LAST:event_txt_name2MouseClicked

    private void rd_PVsPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_PVsPActionPerformed
        PlayerVsPlayer();
    }//GEN-LAST:event_rd_PVsPActionPerformed

    private void rd_PVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_PVCActionPerformed
        PlayerVSCom();
    }//GEN-LAST:event_rd_PVCActionPerformed
    public void btn_pause()
            {
        if(!manager.isSansang()) return;
                t.stop();       
        if(JOptionPane.showConfirmDialog(rootPane, "Chọn Yes để tiếp tục chơi nào?", "Thông báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                 t.start();
            }
        else
            btn_pause();
        }
    private void btn_PauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PauseActionPerformed
      btn_pause();         
    }//GEN-LAST:event_btn_PauseActionPerformed

    private void rd_ChanHaiDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_ChanHaiDauActionPerformed
        if(rd_ChanHaiDau.isSelected())
            manager.setChan2(true);
        else
            manager.setChan2(false);
    }//GEN-LAST:event_rd_ChanHaiDauActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        help();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(GD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BanCo;
    private javax.swing.JButton btn_Exit;
    private javax.swing.JButton btn_Help;
    private javax.swing.JButton btn_Pause;
    private javax.swing.JMenuItem btn_Quit;
    private javax.swing.JButton btn_Redo;
    private javax.swing.JButton btn_Start;
    private javax.swing.JButton btn_Undo;
    private javax.swing.JButton btn_resetName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem_PlayerVsCom;
    private javax.swing.JMenuItem jMenuItem_PlayerVsPlayer;
    private javax.swing.JMenu jMenu_Help;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lbl_avtPlayer;
    private javax.swing.JLabel lbl_mess;
    private javax.swing.JLabel lbl_thongbao;
    private javax.swing.JLabel lblavt_Game;
    private javax.swing.JMenuItem menu_Redo;
    private javax.swing.JMenuItem menu_Undo;
    private javax.swing.JPanel pnl_thongbao;
    private javax.swing.JProgressBar pro_Bar;
    private javax.swing.JCheckBox rd_ChanHaiDau;
    private javax.swing.JCheckBox rd_PVC;
    private javax.swing.JCheckBox rd_PVsP;
    private javax.swing.JTextField txt_name1;
    private javax.swing.JTextField txt_name2;
    // End of variables declaration//GEN-END:variables
}
