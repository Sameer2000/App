package com.housee.app.controller;

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.housee.app.dao.ChartDaoImpl;
import com.housee.app.json.UserMonthlyChart;

@Controller
@RequestMapping("/admin/chart")
public class ChartController {

	@Autowired
	ChartDaoImpl chartDao;
	
	@RequestMapping(value="admin_reg_chart")
	public ModelAndView regChart(){
		ModelAndView mv = new ModelAndView("admin_reg_chart");
		return mv;
	}
	
	@RequestMapping(value="admin_tra_chart")
	public ModelAndView traChart(){
		ModelAndView mv = new ModelAndView("admin_tra_chart");
		return mv;
	}
	
	@RequestMapping(value="regchart")
	public void generateRegisterChart(@RequestParam(value="v", required=false) String viewOption, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Generate Registration Chart --> ");		
		List<UserMonthlyChart> chartList = chartDao.getMonthlyChart("", "", "", "");		
		response.setContentType("image/png");	
		String s = "Monthly User List";
		CategoryDataset dSet = createDataset(chartList,s);
		
		JFreeChart chart = createChart(dSet,s);
		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 400, 400);			
			response.getOutputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	
	private CategoryDataset createDataset(List<UserMonthlyChart> chartList, String s) {
        // row keys...
        final String series1 = "Total User";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if(s.equals("Monthly User List")){
		for(UserMonthlyChart chart : chartList){
	        dataset.addValue(Double.valueOf(chart.getValue()), series1, chart.getMonth()+"/"+chart.getYear());			
			}
        }
        else{
        	for(UserMonthlyChart chart : chartList){
    	        dataset.addValue(Double.valueOf(chart.getValue()), series1, chart.getYear());			
    			}
        }
        return dataset;
    }
	
	private JFreeChart createChart(final CategoryDataset dataset, String title) {
    
	    // create the chart...
	    final JFreeChart chart = ChartFactory.createBarChart(
	        "",         // chart title
	        "",               // domain axis label
	        "Value",                  // range axis label
	        dataset,                  // data
	        PlotOrientation.VERTICAL, // orientation
	        true,                     // include legend
	        true,                     // tooltips?
	        false                     // URLs?
	    );	 
	
	    // set the background color for the chart...
	    chart.setBackgroundPaint(Color.white);
	    chart.setTitle(title);
	   
	
	    // get a reference to the plot for further customisation...
	    final CategoryPlot plot = chart.getCategoryPlot();
	    plot.setBackgroundPaint(Color.lightGray);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	
	    // set the range axis to display integers only...
	    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    rangeAxis.setUpperMargin(0.10);
	
	    // disable bar outlines...
	    final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setDrawBarOutline(false);
	    renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    
	    // set up gradient paints for series...
	    final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
	    final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
	    final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
	    final GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.cyan, 0.0f, 0.0f, Color.lightGray);
	    final GradientPaint gp4 = new GradientPaint(0.0f, 0.0f, Color.magenta, 0.0f, 0.0f, Color.lightGray);
	    final GradientPaint gp5 = new GradientPaint(0.0f, 0.0f, Color.pink, 0.0f, 0.0f, Color.lightGray);
	    final GradientPaint gp6 = new GradientPaint(0.0f, 0.0f, Color.darkGray, 0.0f, 0.0f, Color.lightGray);
	    renderer.setSeriesPaint(0, gp0);
	    renderer.setSeriesPaint(1, gp1);
	    renderer.setSeriesPaint(2, gp2);
	    //---------------Dec.10----------
	    renderer.setSeriesPaint(3, gp3);
	    renderer.setSeriesPaint(4, gp4);
	    renderer.setSeriesPaint(5, gp5);
	    renderer.setSeriesPaint(6, gp6);
	    
	
	    final CategoryAxis domainAxis = plot.getDomainAxis();	    
	    domainAxis.setCategoryLabelPositions(
	    		CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));	    
	    
	    return chart;
    
	}
	
	
	//---------------Yearly----------Registration------------Chart----------------------
	@RequestMapping(value="regchart2")
	public void generateRegisterChart2(@RequestParam(value="v", required=false) String viewOption, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Generate Registration Chart --> ");		
		List<UserMonthlyChart> chartList2 = chartDao.getYearlyChart("", "", "", "");		
		response.setContentType("image/png");
		String s = "Yearly User List";
		CategoryDataset dSet2 = createDataset(chartList2, s);
		
		JFreeChart chart2 = createChart(dSet2,s);
		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart2, 400, 400);			
			response.getOutputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	
	//---------------Monthly----------Transaction------------Chart----------------------
	@RequestMapping(value="trachart")
	public void generateTransactionChart(@RequestParam(value="v", required=false) String viewOption, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Generate Registration Chart --> ");
		
		List<UserMonthlyChart> chartList = chartDao.getMonthlyTransactionChart("", "", "", "");		
		response.setContentType("image/png");
		String s = "Monthly Transaction List";
		CategoryDataset TradSet = createTraDataset(chartList,  s);
		/*String s = "Monthly Transaction List";*/
		JFreeChart trachart = createChart(TradSet,s);		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), trachart, 900, 400);			
			response.getOutputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}	
	
	private CategoryDataset createTraDataset(List<UserMonthlyChart> chartList, String s) {
        // row keys...
        final String series1 = "Total-Recharge-Amount";
        final String series2 = "Total-Service-Fee-Amount";
        final String series3 = "Total-Available-Balance";
        
        //changes------------------9/Dec.---------------
        final String series4 = "Total-Win-Game-Fee";
        final String series5 = "Total-Redeem";
        final String series6 = "Total-Played-Game-Fee";
        final String series7 = "Total-KYL-Amount";
        //----------------------------------------------
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(s.equals("Monthly Transaction List")){
        	
		for(UserMonthlyChart chart : chartList){
	        dataset.addValue(Double.valueOf(chart.getValue()), series1, chart.getMonth()+"/"+chart.getYear());
	        dataset.addValue(Double.valueOf(chart.getValue1()), series2, chart.getMonth()+"/"+chart.getYear());
	        dataset.addValue(Double.valueOf(chart.getValue2()), series3, chart.getMonth()+"/"+chart.getYear());
	        //---------------Dec.10---------------
	        dataset.addValue(Double.valueOf(chart.getValue3()), series4, chart.getMonth()+"/"+chart.getYear());
	        dataset.addValue(Double.valueOf(chart.getValue4()), series5, chart.getMonth()+"/"+chart.getYear());
	        dataset.addValue(Double.valueOf(chart.getValue5()), series6, chart.getMonth()+"/"+chart.getYear());
	        dataset.addValue(Double.valueOf(chart.getValue6()), series7, chart.getMonth()+"/"+chart.getYear());
	        //-------------------------------------
			}
        }
        else{
        	for(UserMonthlyChart chart : chartList){
    	        dataset.addValue(Double.valueOf(chart.getValue()), series1, chart.getYear());
    	        dataset.addValue(Double.valueOf(chart.getValue1()), series2, chart.getYear());
    	        dataset.addValue(Double.valueOf(chart.getValue2()), series3, chart.getYear());
    	        //---------------Dec.10---------------
    	        dataset.addValue(Double.valueOf(chart.getValue3()), series4, chart.getYear());
    	        dataset.addValue(Double.valueOf(chart.getValue4()), series5, chart.getYear());
    	        dataset.addValue(Double.valueOf(chart.getValue5()), series6, chart.getYear());
    	        dataset.addValue(Double.valueOf(chart.getValue6()), series7, chart.getYear());
        		}
        	}
        return dataset;
    }	
	
	//---------------Yearly----------Transaction------------Chart----------------------
	
	@RequestMapping(value="trachart2")
	public void generateTransactionChart2(@RequestParam(value="v", required=false) String viewOption, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Generate Registration Chart --> ");		
		List<UserMonthlyChart> chartList2 = chartDao.getYearlyTransactionChart("", "", "", "");		
		response.setContentType("image/png");
		String s = "Yearly Transaction List";
		CategoryDataset tradSet2 = createTraDataset(chartList2, s);
		/*String s = "Yearly Transaction List";*/
		JFreeChart trachart2 = createChart(tradSet2,s);
		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), trachart2, 900, 400);			
			response.getOutputStream().close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
