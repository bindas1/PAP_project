package org.example;

import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class DynamicTable{
    public TableView buildData(String table) {
        try{
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            TableView tableview = new TableView();
            Database database = new Database();
            ResultSet rs = database.selectRecord(table);

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");

                while(rs.next()){
                    //Iterate Row
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for(int k=1 ; k<=rs.getMetaData().getColumnCount(); k++){
                        //Iterate Column
                        row.add(rs.getString(k));
                    }
                    System.out.println("Row [1] added "+row );
                    data.add(row);

                }

                //FINALLY ADDED TO TableView
                tableview.setItems(data);
            }
            return tableview;

        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        return null;
    }
}
