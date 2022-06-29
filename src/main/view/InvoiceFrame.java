package main.view;

import main.controller.BtnAction;
import main.controller.InOut;
import main.controller.Selection;
import main.model.InvoiceHeader;
import main.model.InvoiceLine;
import main.model.InvoiceTableModel;
import main.model.LineTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InvoiceFrame extends javax.swing.JFrame implements ListSelectionListener {

    public InvoiceFrame(){
        initComponents();
    }
    @SuppressWarnings("unchecked")

    private void initComponents() {
        InvoiceNumLbl = new javax.swing.JLabel();
        InvoiceDateTxt = new javax.swing.JTextField();
        CustomerNameTxt = new javax.swing.JTextField();
        InvoiceTotalLbl = new javax.swing.JLabel();
        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        Label3 = new javax.swing.JLabel();
        Label4 = new javax.swing.JLabel();
        Panel1 = new javax.swing.JPanel();
        Panel2 = new javax.swing.JPanel();
        InvoiceTbl = new javax.swing.JTable();
        InvoiceTbl.getSelectionModel().addListSelectionListener(this);
        LineTbl = new javax.swing.JTable();
        LineTbl.getSelectionModel().addListSelectionListener(LineTableActionListener);
        ScrollPane1 = new javax.swing.JScrollPane();
        ScrollPane2 = new javax.swing.JScrollPane();
        CreateNewInvoiceBtn = new javax.swing.JButton();
        DeleteInvoiceBtn = new javax.swing.JButton();
        AddNewItemBtn = new javax.swing.JButton();
        RemoveItemBtn = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        Menu = new javax.swing.JMenu();
        LoadFile = new javax.swing.JMenuItem();
        SaveFile = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sales Invoice Generator");

        CreateNewInvoiceBtn.setText("Create New Invoice");
        CreateNewInvoiceBtn.setEnabled(false);
        CreateNewInvoiceBtn.addActionListener(InvoiceTableActionListener);

        DeleteInvoiceBtn.setText("Delete Invoice");
        DeleteInvoiceBtn.setEnabled(false);
        DeleteInvoiceBtn.addActionListener(InvoiceTableActionListener);

        Label1.setText("Invoice Number :");

        Label2.setText("Invoice Date :");

        InvoiceDateTxt.setEditable(false);
        InvoiceDateTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvoiceDateTxtActionPerformed(evt);
            }
        });

        Label3.setText("Customer Name :");

        CustomerNameTxt.setEditable(false);
        CustomerNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerNameTxtActionPerformed(evt);
            }
        });

        Label4.setText("Invoice Total :");

        Panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Invoices Table"));

        InvoiceTbl.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                        "No.", "Date", "Customer", "Total"
                }
        ));
        InvoiceTbl.setGridColor(new java.awt.Color(0, 0, 0));
        InvoiceTbl.setShowGrid(true);
        ScrollPane1.setViewportView(InvoiceTbl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                .addContainerGap())
        );

        Panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Invoice Items"));

        LineTbl.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Item Name", "Item Price", "Count", "Item Total"
                }
        ));
        LineTbl.setGridColor(new java.awt.Color(0, 0, 0));
        LineTbl.setShowGrid(true);
        ScrollPane2.setViewportView(LineTbl);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ScrollPane2)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                .addContainerGap())
        );

        AddNewItemBtn.setText("Add New Item");
        AddNewItemBtn.setEnabled(false);
        AddNewItemBtn.addActionListener(LineTableActionListener);

        RemoveItemBtn.setText("Remove Selected Item");
        RemoveItemBtn.setEnabled(false);
        RemoveItemBtn.addActionListener(LineTableActionListener);

        Menu.setText("File");

        LoadFile.setText("Load File");
        LoadFile.addActionListener(LoadSaveFileActionListener);
        LoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadFileActionPerformed(evt);
            }
        });
        Menu.add(LoadFile);

        SaveFile.setText("Save File");
        SaveFile.setEnabled(false);
        SaveFile.addActionListener(LoadSaveFileActionListener);
        SaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveFileActionPerformed(evt);
            }
        });
        Menu.add(SaveFile);

        MenuBar.add(Menu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(69, 69, 69)
                                                .addComponent(CreateNewInvoiceBtn)
                                                .addGap(87, 87, 87)
                                                .addComponent(DeleteInvoiceBtn)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(Label4)
                                                                        .addComponent(Label3))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(CustomerNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(InvoiceTotalLbl)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(Label2)
                                                                        .addComponent(Label1))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(InvoiceNumLbl)
                                                                        .addComponent(InvoiceDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(76, 76, 76)
                                                                .addComponent(AddNewItemBtn)
                                                                .addGap(53, 53, 53)
                                                                .addComponent(RemoveItemBtn)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(Label1)
                                                        .addComponent(InvoiceNumLbl))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(InvoiceDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Label2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(CustomerNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Label3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(InvoiceTotalLbl)
                                                        .addComponent(Label4))
                                                .addGap(7, 7, 7)
                                                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(AddNewItemBtn)
                                        .addComponent(RemoveItemBtn)
                                        .addComponent(DeleteInvoiceBtn)
                                        .addComponent(CreateNewInvoiceBtn))
                                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }

    public void LoadFileActionPerformed(java.awt.event.ActionEvent evt) {}

    public void InvoiceDateTxtActionPerformed(java.awt.event.ActionEvent evt) {}

    public void CustomerNameTxtActionPerformed(java.awt.event.ActionEvent evt) {}

    public void SaveFileActionPerformed(java.awt.event.ActionEvent evt){}

    public static void main(String[] args){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InvoiceFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InvoiceFrame().setVisible(true);
            }
        });
    }
    public javax.swing.JTable LineTbl;
    public javax.swing.JButton AddNewItemBtn;
    public javax.swing.JButton CreateNewInvoiceBtn;
    public javax.swing.JTextField CustomerNameTxt;
    public javax.swing.JButton DeleteInvoiceBtn;
    public javax.swing.JTextField InvoiceDateTxt;
    public javax.swing.JLabel InvoiceNumLbl;
    public javax.swing.JLabel InvoiceTotalLbl;
    public javax.swing.JTable InvoiceTbl;
    public javax.swing.JLabel Label1;
    public javax.swing.JLabel Label2;
    public javax.swing.JLabel Label3;
    public javax.swing.JLabel Label4;
    public javax.swing.JMenu Menu;
    public javax.swing.JMenuBar MenuBar;
    public javax.swing.JPanel Panel2;
    public javax.swing.JPanel Panel1;
    public javax.swing.JScrollPane ScrollPane1;
    public javax.swing.JScrollPane ScrollPane2;
    public javax.swing.JMenuItem LoadFile;
    public javax.swing.JButton RemoveItemBtn;
    public javax.swing.JMenuItem SaveFile;

    public InOut LoadSaveFileActionListener = new InOut(this);
    public BtnAction InvoiceTableActionListener = new BtnAction(this);
    public Selection LineTableActionListener = new Selection(this);

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public ArrayList <InvoiceHeader> InvTbl;
    public ArrayList <InvoiceLine> LnTbl;
    public InvoiceTableModel InvTblObj;
    public LineTableModel LnTblObj;

    public void setInvTbl(ArrayList <InvoiceHeader> InvTable)
    {
        this.InvTbl = InvTable;
    }

    public ArrayList <InvoiceHeader> getInvTbl()
    {
        return InvTbl;
    }

    public void setLnTbl(ArrayList <InvoiceLine> DetInvoice)
    {
        this.LnTbl = DetInvoice;
    }

    public InvoiceHeader getInvTblObj(int InvNumber)
    {
        for(InvoiceHeader Invoice : InvTbl)
        {
            if (Invoice.InvoiceNumber == InvNumber)
            {
                return Invoice;
            }
        }
        return null;
    }

    public void setInvTblObj(InvoiceTableModel InvTblObject)
    {
        this.InvTblObj = InvTblObject;
    }

    @Override
    public void valueChanged(ListSelectionEvent e){
        int InvoiceIndex = InvoiceTbl.getSelectedRow();

        if (InvoiceIndex >= 0){
            AddNewItemBtn.setEnabled(true);
            DeleteInvoiceBtn.setEnabled(true);

            InvoiceHeader row = InvTblObj.InvoiceHeaderTable.get(InvoiceIndex);

            InvoiceNumLbl.setText("" + row.InvoiceNumber);
            //InvoiceDateTxt.setText(dateFormat.format(String.valueOf(row.InvoiceDate)));
            CustomerNameTxt.setText(row.CustomerName);
            InvoiceTotalLbl.setText("" + row.getInvoiceTotal());

            ArrayList <InvoiceLine> InvLn = row.InvoiceLineTable;
            LnTblObj = new LineTableModel(InvLn);
            LineTbl.setModel(LnTblObj);
            LnTblObj.fireTableDataChanged();
        }
        else if (InvoiceIndex < 0){
            DeleteInvoiceBtn.setEnabled(false);
            AddNewItemBtn.setEnabled(false);
        }

        if (InvTblObj.InvoiceHeaderTable.isEmpty()){
            AddNewItemBtn.setEnabled(false);
            DeleteInvoiceBtn.setEnabled(false);
        }
    }

    public void setLineTbl(JTable lineTbl){
        LineTbl = lineTbl;
    }
}
