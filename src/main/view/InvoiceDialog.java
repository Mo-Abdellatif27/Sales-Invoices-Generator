package main.view;

import main.controller.BtnAction;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoiceDialog extends JDialog{
    public JLabel InvDateLbl;
    public JTextField InvDateTxt;
    public JLabel InvCustLbl;
    public JTextField InvCustTxt;
    public JButton Ok;
    public JButton Cancel;

    public InvoiceDialog(BtnAction btnAction){
        InvDateLbl = new JLabel("       Invoice Date: ");
        InvDateTxt =new JTextField(15);
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentDate = LocalDateTime.now();
        try{
            InvDateTxt = new JTextField(DTF.format(currentDate));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        InvCustLbl = new JLabel("       Customer Name: ");
        InvCustTxt = new JTextField(15);
        Ok = new JButton("OK");
        Ok.setActionCommand("OK");
        Ok.addActionListener(btnAction);
        Cancel = new JButton("Cancel");
        Cancel.setActionCommand("Cancel");
        Cancel.addActionListener(btnAction);

        setLayout(new GridLayout(3,2));
        add(InvDateLbl);
        add(InvDateTxt);
        add(InvCustLbl);
        add(InvCustTxt);
        add(Ok);
        add(Cancel);
        pack();
        setTitle("Invoices");
        setLocationRelativeTo(null);
    }
}
