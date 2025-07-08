package com.pharmacy.logic;
import java.util.PriorityQueue;
import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;
import java.util.List;


public class StockMonitoring{
    private static final int Low_Stock_Threshhold = 10;

    public void stockAlert(PriorityQueue<Drug> drugQueue){
        if(drugQueue.isEmpty()){
            System.out.println("\n No Drug Found Out of Stock");
            return;
        }
        System.out.println("\n Stock Alert");
        while(!drugQueue.isEmpty()){
            Drug lowStockDrug = drugQueue.poll();
            System.out.println(lowStockDrug + " Needs Restocking!");
        }

    }
    public void checkDrugsWithLowStock(List<Drug>drugs){
        PriorityQueue<Drug>lowStockDrugs = new PriorityQueue<Drug>();
         
        for(Drug d : drugs){
            if(d.getQuantity()<Low_Stock_Threshhold){
              lowStockDrugs.add(d);
            }

        }
       stockAlert(lowStockDrugs);
    }


}