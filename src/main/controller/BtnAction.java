package main.controller;

import main.model.InvoiceHeader;
import main.model.InvoiceLine;
import main.model.LineTableModel;
import main.view.InvoiceDialog;
import main.view.InvoiceFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static main.view.InvoiceFrame.dateFormat;

public class BtnAction implements ActionListener{
    public InvoiceFrame InvFrObjA;
    public InvoiceDialog InvHdObjA;

    public BtnAction(InvoiceFrame InvFrObjA){
        this.InvFrObjA = InvFrObjA;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if ("Create New Invoice".equals(e.getActionCommand())){
            InvHdObjA = new InvoiceDialog(this);
            InvHdObjA.setVisible(true);
        }

        if("OK".equals(e.getActionCommand())){
            String CustomerName = InvHdObjA.InvCustTxt.getText();
            String DateCheck = InvHdObjA.InvDateTxt.getText();
            InvHdObjA.setVisible(false);
            InvHdObjA.dispose();
            InvHdObjA = null;

            if (CustomerName.trim().length() == 0){
                JOptionPane.showMessageDialog(InvFrObjA, "Please enter the customer name", "Empty Entry",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    Date InvoiceDate = dateFormat.parse(DateCheck);
                    int InvoiceNumber = getHighestInvoiceNum()+1;

                    InvoiceHeader InvoiceTblObj = new InvoiceHeader(InvoiceNumber, InvoiceDate, CustomerName);
                    InvFrObjA.getInvTbl().add(InvoiceTblObj);
                    InvFrObjA.InvTblObj.fireTableDataChanged();

                    int LastInvIndex = InvFrObjA.InvTblObj.getInvoiceHeader().indexOf(InvoiceTblObj);
                    InvFrObjA.InvoiceTbl.setRowSelectionInterval(LastInvIndex , LastInvIndex);
                }
                catch(ParseException exception){
                    JOptionPane.showMessageDialog(InvFrObjA, "Please enter the date such as: dd-MM-yyyy", "Wrong Date Format",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if("Cancel".equals(e.getActionCommand())){
            InvHdObjA.setVisible(false);
            InvHdObjA.dispose();
            InvHdObjA = null;
        }

        else if ("Delete Invoice".equals(e.getActionCommand())){
            int InvIndex = InvFrObjA.InvoiceTbl.getSelectedRow();

            if (InvIndex >= 0){
                InvFrObjA.getInvTbl().remove(InvIndex);
                InvFrObjA.InvTblObj.fireTableDataChanged();

                InvFrObjA.LineTbl.setModel(new LineTableModel(new ArrayList<InvoiceLine>()));
                InvFrObjA.setLnTbl(null);

                InvFrObjA.InvoiceNumLbl.setText("");
                InvFrObjA.InvoiceDateTxt.setText("");
                InvFrObjA.CustomerNameTxt.setText("");
                InvFrObjA.InvoiceTotalLbl.setText("");
            }
            int LastInvIndex = InvFrObjA.InvTblObj.InvoiceHeaderTable.size() - 1;
            if (LastInvIndex >= 0){
                InvFrObjA.InvoiceTbl.setRowSelectionInterval(LastInvIndex , LastInvIndex);
            }
        }
    }

    private int getHighestInvoiceNum(){
        int Highest = 0;
        for (InvoiceHeader InvTblObj : InvFrObjA.getInvTbl()){
            if (InvTblObj.InvoiceNumber > Highest){
                Highest = InvTblObj.InvoiceNumber;
            }
        }
        return Highest;
    }
}
