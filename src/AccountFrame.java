import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class AccountFrame extends JFrame {
    JLabel accNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;

    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;

    JList<Account> accountsLST;
    JPanel p1,p2,p3,p4,p5;

    Set<Account> accountSet = new TreeSet<>();
    Account acc, x;
    boolean newRec = true;

    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;

    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> translist = new ArrayList<>();

    public AccountFrame() {
        super("Account Operations");
        setLayout(null);
        setSize(600, 400);

        accNoLBL = new JLabel("Account No.");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");

        accNoTXT = new JTextField();
        accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField();
        balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150, 25));

        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new City("New Delhi", "Delhi"));
        citiesCMBMDL.addElement(new City("Mumbai", "Maharashtra"));
        citiesCMBMDL.addElement(new City("Pune", "Maharashtra"));
        citiesCMBMDL.addElement(new City("Guwahati", "Assam"));
        citiesCMBMDL.addElement(new City("Amritsar", "Punjab"));
        citiesCMBMDL.addElement(new City("Bangalore", "Karnataka"));
        citiesCMBMDL.addElement(new City("Ahmedabad", "Gujarat"));
        citiesCMBMDL.addElement(new City("Surat", "Gujarat"));
        citiesCMBMDL.addElement(new City("Jodhpur", "Rajasthan"));
        citiesCMBMDL.addElement(new City("Udaipur", "Rajasthan"));
        citiesCMBMDL.addElement(new City("Hyderabad", "Telangana"));
        citiesCMBMDL.addElement(new City("Chennai", "Tamil Nadu"));
        citiesCMBMDL.addElement(new City("Bhopal", "Madhya Pradesh"));
        citiesCMBMDL.addElement(new City("Indore", "Madhya Pradesh"));
        citiesCMBMDL.addElement(new City("Shillong", "Meghalaya"));
        citiesCMBMDL.addElement(new City("Patna", "Bihar"));
        citiesCMBMDL.addElement(new City("Agra", "Uttar Pradesh"));
        citiesCMBMDL.addElement(new City("Kanpur", "Uttar Pradesh"));
        citiesCMBMDL.addElement(new City("Lucknow", "Uttar Pradesh"));
        citiesCMBMDL.addElement(new City("Prayagraj", "Uttar Pradesh"));

        citiesCMB = new JComboBox<City>(citiesCMBMDL);

        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female", true);
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");

        accountsLSTMDL = new DefaultListModel<>();
        accountsLST = new JList<>(accountsLSTMDL);

        p1 = new JPanel(); p1.setBounds(5, 5, 300, 150);
        p1.setLayout(new GridLayout(5, 2));

        p2 = new JPanel(); p2.setBounds(5, 155, 300, 40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5, 195, 600, 40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel(); p4.setBounds(305, 5, 300, 190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5, 240, 580, 120);
        p5.setLayout(new BorderLayout());

        p1.add(accNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountsLST);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("TrsNo");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);


        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accNoTXT.setText("");
                ownerTXT.setText("");
                citiesCMB.setSelectedIndex(0);
                maleRDB.setSelected(true);
                balanceTXT.setText("");
                amountTXT.setText("");
                newRec = true;
            }
        });

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newRec) {
                    if(ownerTXT.getText().length() != 0) {
                        acc = new Account(ownerTXT.getText(), (City) citiesCMB.getSelectedItem(), maleRDB.isSelected() ? 'M' : 'F');
                        accNoTXT.setText(String.valueOf(acc.AccNo));
                        accountSet.add(acc);
                        accountsLSTMDL.addElement(acc);
                        newRec = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                    }
                } else {
                    accountSet.remove(acc);

                    int a = Integer.parseInt(accNoTXT.getText());
                    String o = ownerTXT.getText();
                    City c = (City) citiesCMB.getSelectedItem();
                    char g = maleRDB.isSelected() ? 'M' : 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountSet.add(acc);
                    accountsLSTMDL.setElementAt(acc, accountsLST.getSelectedIndex());
                    newRec = false;
                }
            }
        });

        showBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Account> IA = accountSet.iterator();

                while(IA.hasNext()) {
                    s += IA.next().toString() + "\n";
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });

        depositBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!newRec && amountTXT.getText().length() != 0) {
                    Transaction t = new Transaction(acc, LocalDate.now(), 'D', Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionsInTable(t);
                    acc.deposit(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }
            }
        });

        withdrawBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!newRec && amountTXT.getText().length() != 0) {
                    Transaction t = new Transaction(acc, LocalDate.now(), 'W', Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionsInTable(t);
                    acc.withdraw(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }
            }
        });

        accountsLST.addListSelectionListener((new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                acc = x = accountsLST.getSelectedValue();
                accNoTXT.setText(String.valueOf(acc.AccNo));
                ownerTXT.setText(acc.owner);
                citiesCMB.setSelectedItem(acc.city);

                if(acc.gender == 'M') maleRDB.setSelected(true);
                else femaleRDB.setSelected(true);

                balanceTXT.setText(String.valueOf(acc.balance));
                amountTXT.setEnabled(true);
                depositBTN.setEnabled(true);
                newRec = false;

                for(int i = tableModel.getRowCount() - 1; i>=0; i--) {
                    tableModel.removeRow(i);
                }

                SearchTransactionList(acc.AccNo);
            }
        }));
    }

    private void SearchTransactionList(int AccNo) {
        List<Transaction> filteredList = new ArrayList<>();

        for(Transaction t: translist) {
            if(t.getAcc().AccNo == AccNo) {
                filteredList.add(t);
            }
        }

        for(int i = 0; i < filteredList.size(); i++) {
            tableModel.addRow(new Object[] {
                    filteredList.get(i).getTrsNo(),
                    filteredList.get(i).getDate(),
                    filteredList.get(i).getOperation(),
                    filteredList.get(i).getAmount(),
            });
        }
    }

    private void DisplayTransactionsInTable(Transaction t) {
        tableModel.addRow(new Object[] {
                t.getTrsNo(),
                t.getDate(),
                t.getOperation(),
                t.getAmount()
        });

        translist.add(t);
    }



    public static void main(String[] args) {
        AccountFrame af = new AccountFrame();
        af.setVisible(true);
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
