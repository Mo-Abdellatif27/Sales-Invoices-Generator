package main.model;

import main.view.InvoiceFrame;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    public int InvoiceNumber;
    public Date InvoiceDate;
    public String CustomerName;
    public double InvoiceTotal;
    public ArrayList <InvoiceLine> InvoiceLineTable;

    public InvoiceHeader(int InvoiceNumber, Date InvoiceDate, String CustomerName){
        this.InvoiceNumber = InvoiceNumber;
        this.InvoiceDate = InvoiceDate;
        this.CustomerName = CustomerName;
    }

    public ArrayList <InvoiceLine> getInvoiceLine(){
        if (InvoiceLineTable == null){
            InvoiceLineTable = new ArrayList<>();
        }
        return InvoiceLineTable;
    }

    public double getInvoiceTotal(){
        double InvTotal = 0.0;

        for (int i = 0; i < getInvoiceLine().size() ; i++){
            InvTotal += getInvoiceLine().get(i).getPriceTotal();
        }
        this.InvoiceTotal = InvTotal;
        return this.InvoiceTotal;
    }

    public String getInvoiceHeader(){
        return InvoiceNumber + "," + InvoiceFrame.dateFormat.format(InvoiceDate) + "," + CustomerName;
    }
}
