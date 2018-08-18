package org.vdi.report.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vdi.report.template.TemplateStyles;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

@Component
public class TemplateBuilders {

	private DynamicReportBuilder master;
	
	//for servicedesk report
	private DynamicReport summarySub;
	private DynamicReport sdAgentSub;
	private DynamicReport sdShiftSub;
	private DynamicReport sdEscalationSub;
	
	@Autowired
	public TemplateBuilders(TemplateStyles style) throws ColumnBuilderException, ClassNotFoundException{
		
		this.master = createMaster(style);
		
		//service service desk
		this.summarySub = createSummarySub(style);
		this.sdAgentSub =createSDAgentSub(style);
		this.sdShiftSub = createSDShiftSub(style);
		this.sdEscalationSub = createSDEscalationSub(style);
		
	}
	
	public DynamicReportBuilder createMaster(TemplateStyles style) {

		DynamicReportBuilder drb = new DynamicReportBuilder();
		Integer margin = 20;
		drb.setTitleStyle(style.getDejavuSansTitleStyle())
			.setTitleHeight(60)
			.setSubtitleHeight(15)
			.setLeftMargin(margin).setRightMargin(margin).setTopMargin(20)
			.setBottomMargin(margin).setPrintBackgroundOnOddRows(true)
			.setOddRowBackgroundStyle(style.getOddRowStyle())
			.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
	
		return drb;
		
	}
	
	private DynamicReport createSummarySub(TemplateStyles style) {

		AbstractColumn columnName = ColumnBuilder.getNew().setColumnProperty("name", String.class.getName())
				.setTitle("Achievement").setWidth(new Integer(100)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();

		AbstractColumn columnValue = ColumnBuilder.getNew().setColumnProperty("value", String.class.getName())
				.setTitle("").setWidth(new Integer(100)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();

		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnName);
		drb.addColumn(columnValue);
//		drb.setUseFullPageWidth(true);

		return drb.build();
	}

	private DynamicReport createSDAgentSub(TemplateStyles style) {
		
		AbstractColumn columnAgent = ColumnBuilder.getNew().setColumnProperty("agentName", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTotalAchieved = ColumnBuilder.getNew().setColumnProperty("totalAchieved", Integer.class.getName())
				.setTitle("Achieved").setWidth(new Integer(50)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalMissed = ColumnBuilder.getNew().setColumnProperty("totalMissed", Integer.class.getName())
				.setTitle("Missed").setWidth(new Integer(50)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotal = ColumnBuilder.getNew().setColumnProperty("totalTicket", Integer.class.getName())
				.setTitle("Total").setWidth(new Integer(50)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAchievement = ColumnBuilder.getNew().setColumnProperty("achievement", String.class.getName())
				.setTitle("Achievement").setWidth(new Integer(80)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnAgent);
		drb.addColumn(columnTotalAchieved);
		drb.addColumn(columnTotalMissed);
		drb.addColumn(columnTotal);
		drb.addColumn(columnAchievement);
		
		return drb.build();
	}
	
	private DynamicReport createSDShiftSub(TemplateStyles style) {
		
		AbstractColumn columnShift = ColumnBuilder.getNew().setColumnProperty("shift", String.class.getName())
				.setTitle("Shift").setWidth(new Integer(150)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTotalAchieved = ColumnBuilder.getNew().setColumnProperty("totalAchieved", Integer.class.getName())
				.setTitle("Achieved").setWidth(new Integer(50)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotalMissed = ColumnBuilder.getNew().setColumnProperty("totalMissed", Integer.class.getName())
				.setTitle("Missed").setWidth(new Integer(50)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnTotal = ColumnBuilder.getNew().setColumnProperty("totalTicket", Integer.class.getName())
				.setTitle("Total").setWidth(new Integer(50)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		AbstractColumn columnAchievement = ColumnBuilder.getNew().setColumnProperty("achievement", String.class.getName())
				.setTitle("Achievement").setWidth(new Integer(80)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle()).build();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnShift);
		drb.addColumn(columnTotalAchieved);
		drb.addColumn(columnTotalMissed);
		drb.addColumn(columnTotal);
		drb.addColumn(columnAchievement);
		
		return drb.build();
		
	}
	
	DynamicReport createSDEscalationSub(TemplateStyles style) {
		
		AbstractColumn columnAgent = ColumnBuilder.getNew().setColumnProperty("agent", String.class.getName())
				.setTitle("Agent").setWidth(new Integer(150)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnTotalEscalated = ColumnBuilder.getNew().setColumnProperty("totalEscalated", String.class.getName())
				.setTitle("Total Escalated Ticket").setWidth(new Integer(150)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		AbstractColumn columnEscalatedTo = ColumnBuilder.getNew().setColumnProperty("escalatedTo", String.class.getName())
				.setTitle("Escalated To").setWidth(new Integer(150)).setStyle(style.getArialDetailSummaryStyle())
				.setHeaderStyle(style.getArialHeaderSummaryStyle())
				.build();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.addColumn(columnAgent);
		drb.addColumn(columnTotalEscalated);
		drb.addColumn(columnEscalatedTo);
		
		return drb.build();
	}

	public DynamicReportBuilder getMaster() {
		return master;
	}

	public DynamicReport getSummarySub() {
		return summarySub;
	}

	public DynamicReport getSdAgentSub() {
		return sdAgentSub;
	}

	public DynamicReport getSdShiftSub() {
		return sdShiftSub;
	}

	public DynamicReport getSdEscalationSub() {
		return sdEscalationSub;
	}
	
}
