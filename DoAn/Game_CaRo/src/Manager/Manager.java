/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import static GUI.GD.checkTime;
import static GUI.GD.t;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.omg.CORBA.Current;

/**
 *
 * @author Black Dung
 */
public class Manager implements ActionListener {

    private JPanel jPanel;
    private ArrayList<Player> players;
    private int currentPlayer;

    private JTextField name1, name2;
    private JLabel mark;
    private JLabel messJLabel;
    private JButton oldSB = new JButton();
    //trạng thái
    public boolean sansang = false; // chưa chơi được

    private int TongSoOCo = Size.HEIGHT * Size.WIDTH;
    private int Dem = 0;

    // thanh thời gian
    private JProgressBar progressBar;

    // Stack undo redo
    private Stack<PlayerInfo> CacNuocDaDi;
    private Stack<PlayerInfo> CacNuocUnDo;

    // chế đô chơi:( 1 : chơi với người, 2 : chơi với máy)  
    private int CheDoChoi;
    // check win(chặn 2 đầu và không chặn 2 đầu)
    private boolean Chan2;
    // danh sách button win(5)
    private ArrayList<JButton> arrWin;

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<ArrayList<JButton>> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<ArrayList<JButton>> matrix) {
        this.matrix = matrix;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public JTextField getName1() {
        return name1;
    }

    public void setName1(JTextField name1) {
        this.name1 = name1;
    }

    public JTextField getName2() {
        return name2;
    }

    public void setName2(JTextField name2) {
        this.name2 = name2;
    }

    public JLabel getMark() {
        return mark;
    }

    public void setMark(JLabel mark) {
        this.mark = mark;
    }

    public JLabel getMessJLabel() {
        return messJLabel;
    }

    public void setMessJLabel(JLabel messJLabel) {
        this.messJLabel = messJLabel;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public boolean isSansang() {
        return sansang;
    }

    public void setSansang(boolean sansang) {
        this.sansang = sansang;
    }

    public Stack<PlayerInfo> getCacNuocDaDi() {
        return CacNuocDaDi;
    }

    public void setCacNuocDaDi(Stack<PlayerInfo> CacNuocDaDi) {
        this.CacNuocDaDi = CacNuocDaDi;
    }

    public int getCheDoChoi() {
        return CheDoChoi;
    }

    public void setCheDoChoi(int CheDoChoi) {
        this.CheDoChoi = CheDoChoi;
    }

    public boolean isChan2() {
        return Chan2;
    }

    public void setChan2(boolean Chan2) {
        this.Chan2 = Chan2;
    }

    public ArrayList<JButton> getArrWin() {
        return arrWin;
    }

    public void setArrWin(ArrayList<JButton> arrWin) {
        this.arrWin = arrWin;
    }

    // phương thức khởi tạo
    public Manager() {
    }

    public ArrayList<Player> danhsach() {
        ArrayList<Player> ds = new ArrayList<Player>();
        ImageIcon a = new ImageIcon(new ImageIcon(getClass().getResource("/Image/x.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
        ImageIcon b = new ImageIcon(new ImageIcon(getClass().getResource("/Image/o.png")).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT));
        ImageIcon aa1 = new ImageIcon(new ImageIcon(getClass().getResource("/Image/avtx.png")).getImage().getScaledInstance(250, 227, java.awt.Image.SCALE_DEFAULT));
        ImageIcon bb2 = new ImageIcon(new ImageIcon(getClass().getResource("/Image/avto.png")).getImage().getScaledInstance(250, 227, java.awt.Image.SCALE_DEFAULT));
        Player a1 = new Player("I am bot", a, aa1); //sau này sửa lại lấy tên người ta nhập bên gui lấy qua đây bỏ vô mảng là xon g còn con bot nữa nếu có bot thì add 1,3 bỏ 2(adđ có chọn lọc)
        Player a2 = new Player("namePlayer1", b, bb2);
        ds.add(a1);
        ds.add(a2);
        return ds;
    }

    public Manager(JPanel jPanel, JTextField name1, JTextField name2, JLabel mark, JLabel messJLabel, JProgressBar progressBar) {
        this.jPanel = jPanel;
        this.players = danhsach();
        this.name1 = name1;
        this.name2 = name2;
        this.mark = mark;
        this.messJLabel = messJLabel;
        this.progressBar = progressBar;
        currentPlayer = 0;
        CacNuocUnDo = new Stack<PlayerInfo>();
        this.CheDoChoi = 1;
        this.Chan2 = false;
        this.arrWin = new ArrayList<>();
    }
    // function
    // Tạo bàn cờ bằng dãy button
    ArrayList<ArrayList<JButton>> matrix;

    public void TaoBanCo() {
        this.CacNuocDaDi = new Stack<PlayerInfo>();
        matrix = new ArrayList<ArrayList<JButton>>();
        //this.CacNuocDaDi = new Stack<PlayerInfo>();
        JButton old = new JButton();
        old.setSize(0, 0); // cho width = 0.
        for (int i = 0; i < Size.WIDTH; i++) {
            ArrayList<JButton> ds = new ArrayList<JButton>();
            for (int j = 0; j <= Size.HEIGHT; j++) {
                // tạo và sửa button ..., thêm event click
                JButton btn = new JButton();
                btn.setBackground(Color.WHITE);
                btn.setSize(Size.CHESS_WIDTH, Size.CHESS_HEIGHT);
                btn.setLocation(old.getSize().width + old.getLocation().x, old.getLocation().y);
                btn.addActionListener(this);
                // add vào banco
                jPanel.add(btn);
                //add vào arráylist
                ds.add(btn);
                old = btn;
            }
            matrix.add(i, ds);
            old.setLocation(0, old.getLocation().y + Size.CHESS_WIDTH);
            old.setSize(0, 0);

        }
    }

    // sự kiện click của progressBarutton
    @Override
    public void actionPerformed(ActionEvent e) {

        // kiểm tra người chơi đã sẵn sàng chưa
        if (!sansang) {
            return;
        }
        JButton btn = (JButton) e.getSource();
        if (btn.getIcon() != null) {
            return;
        }
        oldSB.setBackground(Color.white);

        if (progressBar.getValue() == 10) {
            EndGame(currentPlayer);
        }

        // khi đánh cờ thì reset stack redo lại rỗng để kh chứa các vị trí cũ
        CacNuocUnDo = new Stack<PlayerInfo>();
        //
        DanhCo(btn);

        // gọi pro bar
        if (sansang == true) {
            progressBar.setValue(100);
            t.start();
        }
        if (CheDoChoi == 2 && sansang == true) {
            StartComputer();
        }

    }

    public boolean DanhCo(JButton btn) {
        oldSB = btn;
        btn.setBackground(Color.ORANGE);
        // lưu vào stack
        CacNuocDaDi.push(new PlayerInfo(getPoint(btn), currentPlayer));

        //System.err.println((Size.WIDTH) * (Size.HEIGHT));   // số lương ô cờ tối đa
        btn.setIcon(players.get(currentPlayer).getMarkImage()); // thêm ảnh vào nút click
        String text = currentPlayer == 0 ? "x" : "o"; // set text để kiểm tra win hoặc hoà nhau
        btn.setText(text);
        //đếm số lượng cờ đã đánh
        Dem++;
        //System.out.println(Dem);
        // System.out.println(TongSoOCo);
        // Kiêm Tra Coi Có Hoà Chưa
        if (Dem == TongSoOCo) {
            t.stop();
            sansang = false;
            // đóng stack
            CacNuocDaDi = new Stack<PlayerInfo>();
            CacNuocUnDo = new Stack<PlayerInfo>();
            JOptionPane.showMessageDialog(jPanel, "Hoà Nhau Rồi!!");
        }
        // Kiểm Tra Coi Win Chưa ()
        if (isEndGame(btn)) {
            EndGame(currentPlayer);
            return true;
        }
        currentPlayer = currentPlayer == 0 ? 1 : 0;

        ChangePlayer();

        return true;
    }

    // Kiểm Tra Đủ 5 Con Chưa
    private void EndGame(int currentPlayer) {
        t.stop();
        // tô màu 5 ô chiến thắng
        for (int i = 0; i < 5; i++) {
            arrWin.get(i).setBackground(Color.YELLOW);
        }
        arrWin.clear();
        JOptionPane.showMessageDialog(jPanel, players.get(currentPlayer).getName() + " đã giành chiến thắng rồi!");

        sansang = false;
        // đóng stack
        CacNuocDaDi = new Stack<PlayerInfo>();
        CacNuocUnDo = new Stack<PlayerInfo>();
    }

    // Nếu 1 trong 4 phương đủ 5 con  thì sẽ return true > gọi hàm endgame để kết thúc.
    private boolean isEndGame(JButton btn) {
        return KiemTra_Ngang(btn) || KiemTra_Doc(btn) || KiemTra_DCC(btn) || KiemTra_DCP(btn);
    }

    // Lấy toạ độ điểm nhấn tính dòng và cột
    private Point getPoint(JButton btn) {

        int x = btn.getLocation().x / Size.CHESS_WIDTH; //ngang
        int y = btn.getLocation().y / Size.CHESS_HEIGHT; // doc
        //System.err.println("x =" + x+"y=" +y);
        Point point = new Point(x, y);
        return point;
    }

    // check theo từng phương , ngang, dọc ...
    private boolean KiemTra_Ngang(JButton btn) {
        Point point = getPoint(btn);
        int countLeft = 0;
        int demChan2 = 0;
        ArrayList<JButton> ListWin = new ArrayList<>();
        for (int i = point.x; i >= 0; i--) {
            if (matrix.get(point.y).get(i).getText() == btn.getText()) {
                countLeft++;
                ListWin.add(matrix.get(point.y).get(i));
            } else if (matrix.get(point.y).get(i).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        int countRight = 0;
        for (int i = point.x + 1; i <= Size.WIDTH; i++) {
            if (matrix.get(point.y).get(i).getText() == btn.getText()) {
                countRight++;
                ListWin.add(matrix.get(point.y).get(i));
            } else if (matrix.get(point.y).get(i).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        // nếu người chơi chọn chặn 2 bên
        if (demChan2 == 2 && Chan2 == true) {
            return false;
        }
        // nếu đủ 5 con vượt rào
        if (countLeft + countRight == 5) {
            arrWin = ListWin;
        }
        return countLeft + countRight == 5;
    }

    private boolean KiemTra_Doc(JButton btn) {
        Point point = getPoint(btn);
        int countTop = 0;
        int demChan2 = 0;
        ArrayList<JButton> ListWin = new ArrayList<>();
        for (int i = point.y; i >= 0; i--) {
            if (matrix.get(i).get(point.x).getText() == btn.getText()) {
                countTop++;
                ListWin.add(matrix.get(i).get(point.x));
            } else if (matrix.get(i).get(point.x).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        int countBottom = 0;
        for (int i = point.y + 1; i < Size.HEIGHT; i++) {
            if (matrix.get(i).get(point.x).getText() == btn.getText()) {
                countBottom++;
                ListWin.add(matrix.get(i).get(point.x));
            } else if (matrix.get(i).get(point.x).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        // nếu người chơi chọn chặn 2 bên
        if (demChan2 == 2 && Chan2 == true) {
            return false;
        }
        // nếu đủ 5 con vượt rào
        if (countTop + countBottom == 5) {
            arrWin = ListWin;
        }
        //System.err.println("count top " + countBottom+ "countbt" + countBottom);
        return countTop + countBottom == 5;
    }

    private boolean KiemTra_DCC(JButton btn) {
        Point point = getPoint(btn);
        int countLTop = 0;
        int demChan2 = 0;
        ArrayList<JButton> ListWin = new ArrayList<>();
        for (int i = 0; i <= point.x; i++) {
            if (point.x - i < 0 || point.y - i < 0) {
                break;
            }
            if (matrix.get(point.y - i).get(point.x - i).getText() == btn.getText()) {
                countLTop++;
                ListWin.add(matrix.get(point.y - i).get(point.x - i));
            } else if (matrix.get(point.y - i).get(point.x - i).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        int countRBottom = 0;
        for (int i = 1; i <= Size.WIDTH - point.x; i++) {
            if (point.x + i >= Size.WIDTH || point.y + i >= Size.HEIGHT) {
                break;
            }
            if (matrix.get(point.y + i).get(point.x + i).getText() == btn.getText()) {
                countRBottom++;
                ListWin.add(matrix.get(point.y + i).get(point.x + i));
            } else if (matrix.get(point.y + i).get(point.x + i).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        // nếu người chơi chọn chặn 2 bên
        if (demChan2 == 2 && Chan2 == true) {
            return false;
        }
        // nếu đủ 5 con vượt rào
        if (countLTop + countRBottom == 5) {
            arrWin = ListWin;
        }
        //System.err.println("count top " + countLTop+ "countbt" + countRBottom);
        return countLTop + countRBottom == 5;
    }

    private boolean KiemTra_DCP(JButton btn) {
        Point point = getPoint(btn);
        int countRTop = 0;
        int demChan2 = 0;
        ArrayList<JButton> ListWin = new ArrayList<>();
        for (int i = 0; i <= point.x; i++) {
            if (point.x + i > Size.WIDTH || point.y - i < 0) {
                break;
            }
            if (matrix.get(point.y - i).get(point.x + i).getText() == btn.getText()) {
                countRTop++;
                ListWin.add(matrix.get(point.y - i).get(point.x + i));
            } else if (matrix.get(point.y - i).get(point.x + i).getText() != "") {

                demChan2++;
                break;
            } else {
                break;
            }
        }
        int countLBottom = 0;
        for (int i = 1; i <= Size.WIDTH - point.x; i++) {
            if (point.x - i < 0 || point.y + i >= Size.HEIGHT) {
                break;
            }
            if (matrix.get(point.y + i).get(point.x - i).getText() == btn.getText()) {
                countLBottom++;
                ListWin.add(matrix.get(point.y + i).get(point.x - i));
            } else if (matrix.get(point.y + i).get(point.x - i).getText() != "") {
                demChan2++;
                break;
            } else {
                break;
            }
        }
        // nếu người chơi chọn chặn 2 bên
        if (demChan2 == 2 && Chan2 == true) {
            return false;
        }
        // nếu đủ 5 con vượt rào
        if (countRTop + countLBottom == 5) {
            arrWin = ListWin;
        }
        //System.err.println("count top " + countRTop+ "countbt" + countLBottom);
        return countRTop + countLBottom == 5;
    }

    public boolean Undo(JMenuItem a, JButton button) {
        if (CheDoChoi == 2 && CacNuocDaDi.size() == 1) {
            return false;
        }
        // nếu không còn phần tử thì khoá lại
        if (CacNuocDaDi.isEmpty()) {
            a.setEnabled(false);
            button.setEnabled(false);
            return false;
        }
        // pop từ stack ra để lấy toạ độ nút
        PlayerInfo old = CacNuocDaDi.pop();
        JButton btn = matrix.get(old.getPoint().y).get(old.getPoint().x);
        // set cho nó rỗng hết 
        btn.setBackground(Color.WHITE);
        btn.setIcon(null);
        btn.setText("");
        // thêm màu cho nút kế nút undo
        if (CacNuocDaDi.size() > 0) {
            PlayerInfo old1 = CacNuocDaDi.peek();
            matrix.get(old1.getPoint().y).get(old1.getPoint().x).setBackground(Color.ORANGE);
        }
        // thêm vào stack để thực hiện redo nếu có
        CacNuocUnDo.push(new PlayerInfo(getPoint(btn), currentPlayer));
        // nếu stack rỗng thì tức chưa có người bắt đầu sẽ là 0
        if (CacNuocDaDi.isEmpty()) {
            currentPlayer = 0;
        } else // ngược lại thì đổi lượt
        {
            old = CacNuocDaDi.peek();
            currentPlayer = old.getCurentPlayer() == 1 ? 0 : 1;
        }
        ChangePlayer();
        Dem--;
        return true;
    }

    public boolean Redo(JMenuItem a, JButton button) {
        // nếu không còn phần tử thì khoá lại
        if (CacNuocUnDo.isEmpty()) {
            a.setEnabled(false);
            button.setEnabled(false);
            return false;
        }
        if (CacNuocDaDi.size() > 0) {
            PlayerInfo peekInfo = CacNuocDaDi.peek();
            matrix.get(peekInfo.getPoint().y).get(peekInfo.getPoint().x).setBackground(Color.white);
        }
        // lấy phần tử ra
        PlayerInfo info = CacNuocUnDo.pop();
        // chuyển sang nút có toạ độ x,y để thay đổi màu ...
        JButton btn = matrix.get(info.getPoint().y).get(info.getPoint().x);
        // redo tương đương đánh => lưu vô nếu có undo ..
        CacNuocDaDi.push(new PlayerInfo(getPoint(btn), currentPlayer));
        // thêm ảnh vào nút có toạ độ vừa lấy ra, sau đó  set text để kiểm tra win.
        btn.setIcon(players.get(currentPlayer).getMarkImage());
        String text = currentPlayer == 0 ? "x" : "o";
        btn.setText(text);
        Dem++; //đếm số lượng cờ đã đánh, tăng số lượng phần tử
        currentPlayer = currentPlayer == 1 ? 0 : 1; // đổi lượt
        ChangePlayer();
        // đổi màu nút đánh
        btn.setBackground(Color.ORANGE);
        return true;
    }

    private void ChangePlayer() {
        //đổi tên người chơi từ từ tính sau, vấn đề là khởi đọng lại game sẽ nếu là 1 đang chơi thì sẽ tô màu txt1 else
        if (currentPlayer == 0) {
            // name1.setText(players.get(currentPlayer).getName()); // set tên
            name1.setBackground(Color.lightGray);
            name2.setBackground(Color.WHITE);
        } else {
            //name2.setText(players.get(currentPlayer).getName());
            name2.setBackground(Color.lightGray);
            name1.setBackground(Color.WHITE);
        }

        mark.setIcon(players.get(currentPlayer).getMarkImageavt()); // đổi tên click ô
        // cắt tên
        String chuoi = players.get(currentPlayer).getName();
        String[] name = chuoi.split("\\s");

        String mess = "Đã đến lượt của:  " + name[name.length - 1]; // thông báo lượt chơi của người hiên tại
        messJLabel.setText(mess);
        messJLabel.setForeground(Color.BLUE);
        return;
    }

    public void ChoiVoiNguoi() // chế độ chơi == 1 thì gọi
    {
        oldSB = new JButton();
        players.get(0).setName(name1.getText());
        players.get(1).setName(name2.getText());
        Dem = 0;
        CheDoChoi = 1;
        currentPlayer = 0;
        CacNuocDaDi = new Stack<PlayerInfo>();
        CacNuocUnDo = new Stack<PlayerInfo>();
        TaoBanCo();

    }

    public void ChoiVoiMay() // chế độ chơi == 2 thì gọi
    {
        oldSB = new JButton();
        players.get(0).setName(name1.getText());
        players.get(1).setName(name2.getText());
        Dem = 0;
        CheDoChoi = 2;
        currentPlayer = 0;
        CacNuocDaDi = new Stack<PlayerInfo>();
        CacNuocUnDo = new Stack<PlayerInfo>();
        TaoBanCo();
        StartComputer();
    }

    // AI
    private long[] MangTC = {0, 9, 54, 162, 1458, 13112, 118008};
    private long[] MangPN = {0, 3, 27, 99, 729, 6561, 59049};

    public void StartComputer() {
        if (CacNuocDaDi.isEmpty()) {
            JButton btn = matrix.get(Size.WIDTH / 2 + 1).get(Size.HEIGHT / 2 + 1);
            DanhCo(btn);
            progressBar.setValue(100);
            t.start();
        } else {
            Point point = BestMove();
            oldSB.setBackground(Color.white);
            JButton btn = matrix.get(point.x).get(point.y);
            //DanhCo(point.y, point.x);
            DanhCo(btn);
        }

    }

    private Point BestMove() // muc dich di tim [dong, cot] phu hop nhat 
    {
        Point result = new Point();
        long diemMax = 0;
        for (int i = 0; i < Size.HEIGHT; i++) {
            for (int j = 0; j < Size.WIDTH; j++) {
                //System.out.println("i=" + i + "j=" + j);
                if (matrix.get(j).get(i).getText().isEmpty() && !catTia(i, j)) { // j la cot , i la dong
                    String text = currentPlayer == 0 ? "x" : "o"; // truyền quân max(cờ của máy)
                    long TongDiemTanCong = duyetTC_Ngang(i, j, text) + duyetTC_Doc(i, j, text) + duyetTC_CheoXuoi(i, j, text) + duyetTC_CheoNguoc(i, j, text);
                    text = text == "x" ? "o" : "x"; // truyền vào quân cờ min
                    long TongDiemPhongNgu = duyetPN_Ngang(i, j, text) + duyetPN_Doc(i, j, text) + duyetPN_CheoXuoi(i, j, text) + duyetPN_CheoNguoc(i, j, text);
                    long diemTam = TongDiemTanCong > TongDiemPhongNgu ? TongDiemTanCong : TongDiemPhongNgu;
                    if (diemMax < diemTam) // duyệt tìm vị trí có điểm lớn nhất <=> nó là vị trí tốt nhất nếu có hàm định lượng ổn
                    {
                        diemMax = diemTam;
                        result = new Point(j, i);
                    }
                }
            }
        }
        return result;
    }

//    private long DTC_Doc(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//        for (int Dem = 1; Dem < 6 && dong + Dem < Size.HEIGHT; Dem++) {
//            if (matrix.get(cot).get(dong + Dem).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot).get(dong + Dem).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//
//        for (int Dem = 1; Dem < 6 && dong - Dem >= 0; Dem++) {
//            if (matrix.get(cot).get(dong - Dem).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot).get(dong - Dem).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//        if (soQuanDich == 2) {
//            return 0;
//        }
//        diemTong -= MangPN[soQuanDich + 1] * 1.8f;
//        diemTong += MangTC[soQuanTa];
//        return diemTong;
//    }
//
//    private long DTC_Ngang(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//
//        for (int Dem = 1; Dem < 6 && cot + Dem < Size.WIDTH; Dem++) {
//            if (matrix.get(cot + Dem).get(dong).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot + Dem).get(dong).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//        //xuong
//        for (int Dem = 1; Dem < 6 && cot - Dem >= 0; Dem++) {
//            if (matrix.get(cot - Dem).get(dong).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot - Dem).get(dong).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//        if (soQuanDich == 2) {
//            return 0;
//        }
//        diemTong -= MangPN[soQuanDich + 1] * 1.8f;
//        diemTong += MangTC[soQuanTa];
//        return diemTong;
//    }
//
//    private long DTC_DCC(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//
//        for (int Dem = 1; Dem < 6 && cot + Dem < Size.WIDTH && dong + Dem < Size.HEIGHT; Dem++) {
//            if (matrix.get(cot + Dem).get(dong + Dem).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot + Dem).get(dong + Dem).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//
//        for (int Dem = 1; Dem < 6 && cot - Dem >= 0 && dong - Dem >= 0; Dem++) {
//            if (matrix.get(cot - Dem).get(dong - Dem).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot - Dem).get(dong - Dem).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//        if (soQuanDich == 2) {
//            return 0;
//        }
//        diemTong -= MangPN[soQuanDich + 1] * 1.8f;
//        diemTong += MangTC[soQuanTa];
//        return diemTong;
//    }
//
//    private long DTC_DCP(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//
//        for (int Dem = 1; Dem < 6 && cot + Dem < Size.WIDTH && dong - Dem >= 0; Dem++) {
//            if (matrix.get(cot + Dem).get(dong - Dem).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot + Dem).get(dong - Dem).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//        //xuong
//        for (int Dem = 1; Dem < 6 && cot - Dem >= 0 && dong + Dem < Size.HEIGHT; Dem++) {
//            if (matrix.get(cot - Dem).get(dong + Dem).getText() == QuanCo) {
//                soQuanTa++;
//            } else if (matrix.get(cot - Dem).get(dong + Dem).getText() != "") {
//                soQuanDich++;
//                break;
//            } else {
//                break;
//            }
//        }
//        if (soQuanDich == 2) {
//            return 0;
//        }
//        diemTong -= MangPN[soQuanDich + 1] * 1.8f;
//        diemTong += MangTC[soQuanTa];
//        return diemTong;
//    }
//
//    ////////////
//    private long DPN_Doc(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//        //len
//        for (int Dem = 1; Dem < 6 && dong + Dem < Size.HEIGHT; Dem++) {
//            if (matrix.get(cot).get(dong + Dem).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot).get(dong + Dem).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        //xuong
//        for (int Dem = 1; Dem < 6 && dong - Dem >= 0; Dem++) {
//            if (matrix.get(cot).get(dong - Dem).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot).get(dong - Dem).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        if (soQuanTa == 2) {
//            return 0;
//        }
//        diemTong += MangPN[soQuanDich];
//        return diemTong;
//    }
//
//    private long DPN_Ngang(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//
//        for (int Dem = 1; Dem < 6 && cot + Dem < Size.WIDTH; Dem++) {
//            if (matrix.get(cot + Dem).get(dong).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot + Dem).get(dong).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        //xuong
//        for (int Dem = 1; Dem < 6 && cot - Dem >= 0; Dem++) {
//            if (matrix.get(cot - Dem).get(dong).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot - Dem).get(dong).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        if (soQuanTa == 2) {
//            return 0;
//        }
//        diemTong += MangPN[soQuanDich];
//        return diemTong;
//    }
//
//    private long DPN_DCC(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//
//        for (int Dem = 1; Dem < 6 && cot + Dem < Size.WIDTH && dong + Dem < Size.HEIGHT; Dem++) {
//            if (matrix.get(cot + Dem).get(dong + Dem).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot + Dem).get(dong + Dem).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//
//        for (int Dem = 1; Dem < 6 && cot - Dem >= 0 && dong - Dem >= 0; Dem++) {
//            if (matrix.get(cot - Dem).get(dong - Dem).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot - Dem).get(dong - Dem).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        if (soQuanTa == 2) {
//            return 0;
//        }
//        diemTong += MangPN[soQuanDich];
//        return diemTong;
//    }
//
//    private long DPN_DCP(int dong, int cot, String QuanCo) {
//        long diemTong = 0;
//        int soQuanTa = 0;
//        int soQuanDich = 0;
//
//        for (int Dem = 1; Dem < 6 && cot + Dem < Size.WIDTH && dong - Dem >= 0; Dem++) {
//            if (matrix.get(cot + Dem).get(dong - Dem).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot + Dem).get(dong - Dem).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        //xuong
//        for (int Dem = 1; Dem < 6 && cot - Dem >= 0 && dong + Dem < Size.HEIGHT; Dem++) {
//            if (matrix.get(cot - Dem).get(dong + Dem).getText() == QuanCo) {
//                soQuanTa++;
//                break;
//            } else if (matrix.get(cot - Dem).get(dong + Dem).getText() != "") {
//                soQuanDich++;
//            } else {
//                break;
//            }
//        }
//        if (soQuanTa == 2) {
//            return 0;
//        }
//        diemTong += MangPN[soQuanDich];
//        return diemTong;
//    }
    //---------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------------------------
    private int[] MangDiemTanCong = {0, 4, 25, 246, 7300, 6561, 59049};
    private int[] MangDiemPhongNgu = {0, 3, 24, 243, 2197, 19773, 177957};
    //duyệt ngang

    public int duyetTC_Ngang(int dong, int cot, String QuanCo) {
        int DiemTanCong = 0;
        int SoQuanTa = 0;
        int SoQuanDichTrai = 0;
        int SoQuanDichPhai = 0;
        int KhoangTrong = 0;
        //bên phải
        for (int dem = 1; dem <= 4 && cot < Size.WIDTH - 5; dem++) {
            if (matrix.get(cot + dem).get(dong).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }
                SoQuanTa++;
                KhoangTrong++;
            } else if (matrix.get(cot + dem).get(dong).getText() != "") {
                SoQuanDichPhai++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //bên trái
        for (int dem = 1; dem <= 4 && cot > 4; dem++) {
            if (matrix.get(cot - dem).get(dong).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }

                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot - dem).get(dong).getText() != "") {
                SoQuanDichTrai++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //bị chặn 2 đầu khoảng trống không đủ tạo thành 5 nước
        if (SoQuanDichPhai > 0 && SoQuanDichTrai > 0 && KhoangTrong < 4) {
            return 0;
        }
        DiemTanCong -= MangDiemPhongNgu[SoQuanDichPhai + SoQuanDichTrai];
        DiemTanCong += MangDiemTanCong[SoQuanTa];
        return DiemTanCong;
    }
    //duyệt dọc

    public int duyetTC_Doc(int dong, int cot, String QuanCo) {
        int DiemTanCong = 0;
        int SoQuanTa = 0;
        int SoQuanDichTren = 0;
        int SoQuanDichDuoi = 0;
        int KhoangTrong = 0;

        //bên trên
        for (int dem = 1; dem <= 4 && dong > 4; dem++) {
            if (matrix.get(cot).get(dong - dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }
                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot).get(dong - dem).getText() != "") {
                SoQuanDichTren++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //bên dưới
        for (int dem = 1; dem <= 4 && dong < Size.HEIGHT - 5; dem++) {
            if (matrix.get(cot).get(dong + dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }

                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot).get(dong + dem).getText() != "") {
                SoQuanDichDuoi++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //bị chặn 2 đầu khoảng trống không đủ tạo thành 5 nước
        if (SoQuanDichTren > 0 && SoQuanDichDuoi > 0 && KhoangTrong < 4) {
            return 0;
        }

        DiemTanCong -= MangDiemPhongNgu[SoQuanDichTren + SoQuanDichDuoi];
        DiemTanCong += MangDiemTanCong[SoQuanTa];
        return DiemTanCong;
    }
    //chéo xuôi

    public int duyetTC_CheoXuoi(int dong, int cot, String QuanCo) {
        int DiemTanCong = 1;
        int SoQuanTa = 0;
        int SoQuanDichCheoTren = 0;
        int SoQuanDichCheoDuoi = 0;
        int KhoangTrong = 0;

        //bên chéo xuôi xuống
        for (int dem = 1; dem <= 4 && cot < Size.WIDTH - 5 && dong < Size.HEIGHT - 5; dem++) {
            if (matrix.get(cot + dem).get(dong + dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }

                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot + dem).get(dong + dem).getText() != "") {
                SoQuanDichCheoTren++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //chéo xuôi lên
        for (int dem = 1; dem <= 4 && dong > 4 && cot > 4; dem++) {
            if (matrix.get(cot - dem).get(dong - dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }

                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot - dem).get(dong - dem).getText() != "") {
                SoQuanDichCheoDuoi++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //bị chặn 2 đầu khoảng trống không đủ tạo thành 5 nước
        if (SoQuanDichCheoTren > 0 && SoQuanDichCheoDuoi > 0 && KhoangTrong < 4) {
            return 0;
        }

        DiemTanCong -= MangDiemPhongNgu[SoQuanDichCheoTren + SoQuanDichCheoDuoi];
        DiemTanCong += MangDiemTanCong[SoQuanTa];
        return DiemTanCong;
    }
    //chéo ngược

    public int duyetTC_CheoNguoc(int dong, int cot, String QuanCo) {
        int DiemTanCong = 0;
        int SoQuanTa = 0;
        int SoQuanDichCheoTren = 0;
        int SoQuanDichCheoDuoi = 0;
        int KhoangTrong = 0;

        //chéo ngược lên
        for (int dem = 1; dem <= 4 && cot < Size.WIDTH - 5 && dong > 4; dem++) {
            if (matrix.get(cot + dem).get(dong - dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }

                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot + dem).get(dong - dem).getText() != "") {
                SoQuanDichCheoTren++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //chéo ngược xuống
        for (int dem = 1; dem <= 4 && cot > 4 && dong < Size.HEIGHT - 5; dem++) {
            if (matrix.get(cot - dem).get(dong + dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemTanCong += 37;
                }

                SoQuanTa++;
                KhoangTrong++;

            } else if (matrix.get(cot - dem).get(dong + dem).getText() != "") {
                SoQuanDichCheoDuoi++;
                break;
            } else {
                KhoangTrong++;
            }
        }
        //bị chặn 2 đầu khoảng trống không đủ tạo thành 5 nước
        if (SoQuanDichCheoTren > 0 && SoQuanDichCheoDuoi > 0 && KhoangTrong < 4) {
            return 0;
        }

        DiemTanCong -= MangDiemPhongNgu[SoQuanDichCheoTren + SoQuanDichCheoDuoi];
        DiemTanCong += MangDiemTanCong[SoQuanTa];
        return DiemTanCong;
    }

    //duyệt ngang
    public int duyetPN_Ngang(int dong, int cot, String QuanCo) {
        int DiemPhongNgu = 0;
        int SoQuanTaTrai = 0;
        int SoQuanTaPhai = 0;
        int SoQuanDich = 0;
        int KhoangTrongPhai = 0;
        int KhoangTrongTrai = 0;
        boolean ok = false;
        for (int dem = 1; dem <= 4 && cot < Size.WIDTH - 5; dem++) {
            if (matrix.get(cot + dem).get(dong).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot + dem).get(dong).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaTrai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongPhai++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongPhai == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        ok = false;

        for (int dem = 1; dem <= 4 && cot > 4; dem++) {
            if (matrix.get(cot - dem).get(dong).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot - dem).get(dong).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaPhai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongTrai++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongTrai == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        if (SoQuanTaPhai > 0 && SoQuanTaTrai > 0 && (KhoangTrongTrai + KhoangTrongPhai + SoQuanDich) < 4) {
            return 0;
        }

        DiemPhongNgu -= MangDiemTanCong[SoQuanTaPhai + SoQuanTaPhai];
        DiemPhongNgu += MangDiemPhongNgu[SoQuanDich];

        return DiemPhongNgu;
    }

    //duyệt dọc
    public int duyetPN_Doc(int dong, int cot, String QuanCo) {
        int DiemPhongNgu = 0;
        int SoQuanTaTrai = 0;
        int SoQuanTaPhai = 0;
        int SoQuanDich = 0;
        int KhoangTrongTren = 0;
        int KhoangTrongDuoi = 0;
        boolean ok = false;

        //lên
        for (int dem = 1; dem <= 4 && dong > 4; dem++) {
            if (matrix.get(cot).get(dong - dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;

            } else if (matrix.get(cot).get(dong - dem).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaPhai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongTren++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongTren == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        ok = false;
        //xuống
        for (int dem = 1; dem <= 4 && dong < Size.HEIGHT - 5; dem++) {
            //gặp quân địch
            if (matrix.get(cot).get(dong + dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot).get(dong + dem).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaTrai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongDuoi++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongDuoi == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        if (SoQuanTaPhai > 0 && SoQuanTaTrai > 0 && (KhoangTrongTren + KhoangTrongDuoi + SoQuanDich) < 4) {
            return 0;
        }

        DiemPhongNgu -= MangDiemTanCong[SoQuanTaTrai + SoQuanTaPhai];
        DiemPhongNgu += MangDiemPhongNgu[SoQuanDich];
        return DiemPhongNgu;
    }

    //chéo xuôi
    public int duyetPN_CheoXuoi(int dong, int cot, String QuanCo) {
        int DiemPhongNgu = 0;
        int SoQuanTaTrai = 0;
        int SoQuanTaPhai = 0;
        int SoQuanDich = 0;
        int KhoangTrongTren = 0;
        int KhoangTrongDuoi = 0;
        boolean ok = false;

        //lên
        for (int dem = 1; dem <= 4 && dong < Size.HEIGHT - 5 && cot < Size.WIDTH - 5; dem++) {
            if (matrix.get(cot + dem).get(dong + dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot + dem).get(dong + dem).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaPhai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongTren++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongTren == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        ok = false;
        //xuống
        for (int dem = 1; dem <= 4 && dong > 4 && cot > 4; dem++) {
            if (matrix.get(cot - dem).get(dong - dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot - dem).get(dong - dem).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaTrai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongDuoi++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongDuoi == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        if (SoQuanTaPhai > 0 && SoQuanTaTrai > 0 && (KhoangTrongTren + KhoangTrongDuoi + SoQuanDich) < 4) {
            return 0;
        }

        DiemPhongNgu -= MangDiemTanCong[SoQuanTaPhai + SoQuanTaTrai];
        DiemPhongNgu += MangDiemPhongNgu[SoQuanDich];

        return DiemPhongNgu;
    }
    //chéo ngược
    public int duyetPN_CheoNguoc(int dong, int cot, String QuanCo) {
        int DiemPhongNgu = 0;
        int SoQuanTaTrai = 0;
        int SoQuanTaPhai = 0;
        int SoQuanDich = 0;
        int KhoangTrongTren = 0;
        int KhoangTrongDuoi = 0;
        boolean ok = false;
        //lên
        for (int dem = 1; dem <= 4 && dong > 4 && cot < Size.WIDTH - 5; dem++) {

            if (matrix.get(cot + dem).get(dong - dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot + dem).get(dong - dem).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaPhai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongTren++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongTren == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        ok = false;

        //xuống
        for (int dem = 1; dem <= 4 && dong < Size.HEIGHT - 5 && cot > 4; dem++) {
            if (matrix.get(cot - dem).get(dong + dem).getText() == QuanCo) {
                if (dem == 1) {
                    DiemPhongNgu += 9;
                }

                SoQuanDich++;
            } else if (matrix.get(cot - dem).get(dong + dem).getText() != "") {
                if (dem == 4) {
                    DiemPhongNgu -= 170;
                }

                SoQuanTaTrai++;
                break;
            } else {
                if (dem == 1) {
                    ok = true;
                }

                KhoangTrongDuoi++;
            }
        }

        if (SoQuanDich == 3 && KhoangTrongDuoi == 1 && ok) {
            DiemPhongNgu -= 200;
        }

        if (SoQuanTaPhai > 0 && SoQuanTaTrai > 0 && (KhoangTrongTren + KhoangTrongDuoi + SoQuanDich) < 4) {
            return 0;
        }
        
        DiemPhongNgu -= MangDiemTanCong[SoQuanTaTrai + SoQuanTaPhai];
        DiemPhongNgu += MangDiemPhongNgu[SoQuanDich];

        return DiemPhongNgu;
    }

    boolean catTia(int dong, int cot) {
        //nếu cả 4 hướng đều không có nước cờ thì cắt tỉa
        if (catTiaNgang(dong, cot) && catTiaDoc(dong, cot) && catTiaDCC(dong, cot) && catTiaDCP(dong, cot)) {
            return true;
        }

        //chạy đến đây thì 1 trong 4 hướng vẫn có nước cờ thì không được cắt tỉa
        return false;
    }
    boolean catTiaNgang(int dong, int cot) {
        //duyệt bên phải
        if (cot <= Size.WIDTH - 5) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot + i).get(dong).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }

        //duyệt bên trái
        if (cot >= 4) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot - i).get(dong).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }

        //nếu chạy đến đây tức duyệt 2 bên đều không có nước đánh thì cắt tỉa
        return true;
    }
    boolean catTiaDoc(int dong, int cot) {
        //duyệt phía giưới
        if (dong <= Size.HEIGHT - 5) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot).get(dong + i).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }

        //duyệt phía trên
        if (dong >= 4) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot).get(dong - i).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }

        //nếu chạy đến đây tức duyệt 2 bên đều không có nước đánh thì cắt tỉa
        return true;
    }
    boolean catTiaDCP(int dong, int cot) {
        //duyệt từ trên xuống
        if (dong <= Size.HEIGHT - 5 && cot >= 4) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot - i).get(dong + i).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }

        //duyệt từ dưới lên
        if (cot <= Size.WIDTH - 5 && dong >= 4) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot + i).get(dong - i).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }

        //nếu chạy đến đây tức duyệt 2 bên đều không có nước đánh thì cắt tỉa
        return true;
    }
    boolean catTiaDCC(int dong, int cot) {
        //duyệt từ trên xuống
        if (dong <= Size.HEIGHT - 5 && cot <= Size.WIDTH - 5) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot + i).get(dong + i).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }
        //duyệt từ dưới lên
        if (cot >= 4 && dong >= 4) {
            for (int i = 1; i <= 4; i++) {
                if (matrix.get(cot - i).get(dong - i).getText() != "")//nếu có nước cờ thì không cắt tỉa
                {
                    return false;
                }
            }
        }
        //nếu chạy đến đây tức duyệt 2 bên đều không có nước đánh thì cắt tỉa
        return true;
    }
}
