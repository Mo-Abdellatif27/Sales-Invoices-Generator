package main.model;

public class InvoiceLine{
    public String ItemName;
    public double ItemPrice;
    public int ItemCount;
    public double PriceTotal;
    public InvoiceHeader InvHdObj;

    public InvoiceLine(String ItemName, double ItemPrice, int ItemCount, InvoiceHeader InvHdObj){
        this.ItemName = ItemName;
        this.ItemPrice = ItemPrice;
        this.ItemCount = ItemCount;
        this.InvHdObj = InvHdObj;
    }

    public double getPriceTotal(){
        PriceTotal = ItemPrice * ItemCount;
        return PriceTotal;
    }

    public String getInvoiceLine(){
        return InvHdObj.InvoiceNumber + "," + ItemName + "," + ItemPrice + "," + ItemPrice;
    }
}