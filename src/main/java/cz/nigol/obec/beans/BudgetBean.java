package cz.nigol.obec.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import cz.nigol.obec.entities.DeskItem;
import cz.nigol.obec.entities.BudgetFulfillment;
import cz.nigol.obec.enums.OfficialDeskCategory;
import cz.nigol.obec.services.OfficialDeskService;
import cz.nigol.obec.services.BudgetService;

@Named
@ViewScoped
public class BudgetBean implements Serializable {
    private static final long serialVersionUID = 6087026979515238258L;
    @Inject
    private OfficialDeskService officialDeskService;
    @Inject
    private BudgetService budgetService;
    private List<DeskItem> deskItems;
    private BudgetFulfillment budgetFulfillment;
    private DonutChartModel incomeChart;
    private DonutChartModel expenditureChart;

    @PostConstruct
    public void init() {
        deskItems = officialDeskService.getDeskItemsByCategory(OfficialDeskCategory.ROZPOCET, new Date());
        budgetFulfillment = budgetService.getLatestBudgetFulfillment();
        if (budgetFulfillment != null) {
            prepareChartsData();
        }
    }

    private void prepareChartsData() {
        prepareIncomeChart();
        prepareExpenditureChart();
    }

    private void prepareExpenditureChart() {
        expenditureChart = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(budgetFulfillment.getExpenditureReal());
        values.add(budgetFulfillment.getExpenditurePlan().subtract(budgetFulfillment.getExpenditureReal()));
        dataSet.setData(values);
        List<String> colors = new ArrayList<>();
        colors.add("rgb(255, 99, 132)");
        colors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(colors);
        List<String> labels = new ArrayList<>();
        labels.add("Skutečnost v tis. Kč");
        labels.add("Zbývá v tis. Kč");
        data.setLabels(labels);
        data.addChartDataSet(dataSet);
        expenditureChart.setData(data);
    }

    private void prepareIncomeChart() {
        incomeChart = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(budgetFulfillment.getIncomeReal());
        values.add(budgetFulfillment.getIncomePlan().subtract(budgetFulfillment.getIncomeReal()));
        dataSet.setData(values);
        List<String> colors = new ArrayList<>();
        colors.add("rgb(255, 99, 132)");
        colors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(colors);
        List<String> labels = new ArrayList<>();
        labels.add("Skutečnost v tis. Kč");
        labels.add("Zbývá v tis. Kč");
        data.setLabels(labels);
        data.addChartDataSet(dataSet);
        incomeChart.setData(data);
    }

    public BudgetFulfillment getBudgetFulfillment() {
        return budgetFulfillment;
    }

    public void setBudgetFulfillment(BudgetFulfillment budgetFulfillment) {
        this.budgetFulfillment = budgetFulfillment;
    }

    /**
     * @return the incomeChart
     */
    public DonutChartModel getIncomeChart() {
        return incomeChart;
    }

    /**
     * @param incomeChart the incomeChart to set
     */
    public void setIncomeChart(DonutChartModel incomeChart) {
        this.incomeChart = incomeChart;
    }

    /**
     * @return the expenditureChart
     */
    public DonutChartModel getExpenditureChart() {
        return expenditureChart;
    }

    /**
     * @param expenditureChart the expenditureChart to set
     */
    public void setExpenditureChart(DonutChartModel expenditureChart) {
        this.expenditureChart = expenditureChart;
    }

    /**
     * @return the deskItems
     */
    public List<DeskItem> getDeskItems() {
        return deskItems;
    }

    /**
     * @param deskItems the deskItems to set
     */
    public void setDeskItems(List<DeskItem> deskItems) {
        this.deskItems = deskItems;
    }
}
