package ba.unsa.etf.si.TelefonskeNarudzbe.Controllers;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends JFrame
{
	private Object[][] podacig;
   public BarChart_AWT( String naslov , String nazivGrafa, Object [][] podaci )
   {
	 
      super( naslov );
      podacig=podaci;
      JFreeChart barChart = ChartFactory.createBarChart(
         nazivGrafa,           
         "Vrijeme",            
         "Posotak",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset( )
   {
        
      final DefaultCategoryDataset dataset =  new DefaultCategoryDataset( );  
     
      String x="0";
      Double y=(double) 0;
      for(Object[] o: podacig){
      dataset.addValue( Double.parseDouble( (String) o[2] ) , "Postotak narudzbi" , (String)o[0] );
     
      }           

     
      return dataset; 
   }
   
}