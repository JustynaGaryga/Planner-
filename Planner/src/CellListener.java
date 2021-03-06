import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CellListener implements ListSelectionListener {

	DefaultComboBoxModel<User> usersList;
	ArrayList<Task> tasks;
	ArrayList<User> users;
	DefaultComboBoxModel<Task> tasksList;
	DefaultComboBoxModel<Task> cellTasks;
	DefaultComboBoxModel<Task> tasksToday;
	DefaultComboBoxModel<Task> tasksTomorrow;
	JTable calendar;
	Calendar cal;
	
	public CellListener(DefaultComboBoxModel<User> usersList, ArrayList<Task> tasks, ArrayList<User> users, DefaultComboBoxModel<Task> tasksList,
			DefaultComboBoxModel<Task> tasksToday, DefaultComboBoxModel<Task> tasksTomorrow, JTable calendar, Calendar cal) {
		this.usersList= usersList;
		this.tasks= tasks;
		this.users= users;
		this.tasksList= tasksList;
		this.cellTasks = new DefaultComboBoxModel<Task>();
		this.tasksToday = tasksToday;
		this.tasksTomorrow = tasksTomorrow;
		this.calendar= calendar;
		this.cal= cal;
	}
	
	// button for adding new task
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		// lists with years, months, days, hours and minutes- JComboBoxes in dialog for adding a new task
		DefaultComboBoxModel<String> dayListS = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> dayListE = new DefaultComboBoxModel<>();
		JList listWithDayS = new JList(dayListS);
		JList listWithDaysE = new JList(dayListE);
		for (int i = 1; i <= 31; i++) {
			dayListS.addElement(String.format("%02d", i));
			dayListE.addElement(String.format("%02d", i));
		}
			
		DefaultComboBoxModel<String> monthListS = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> monthListE = new DefaultComboBoxModel<>();
		JList listWithMonthS = new JList(monthListS);
		JList listWithMonthE = new JList(monthListE);
		for (int i = 1; i <= 12; i++) {
			monthListS.addElement(String.format("%02d", i));
			monthListE.addElement(String.format("%02d", i));
		}
			
		DefaultComboBoxModel<String> yearListS = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> yearListE = new DefaultComboBoxModel<>();
		JList listWithYearS = new JList(yearListS);
		JList listWithYearE = new JList(yearListE);
		for (int i = Calendar.getInstance().get(Calendar.YEAR); i < Calendar.getInstance().get(Calendar.YEAR) + 20; i++) {
			yearListS.addElement(String.format("%02d", i));
			yearListE.addElement(String.format("%02d", i));
		}
		
		DefaultComboBoxModel<String> hourListS = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> hourListE = new DefaultComboBoxModel<>();
		JList listWithHourS = new JList(hourListS);
		JList listWithHourE = new JList(hourListE);
		for (int i = 0; i < 24; i++) {
			hourListS.addElement(String.format("%02d", i));
			hourListE.addElement(String.format("%02d", i));
		}
			
		DefaultComboBoxModel<String> minuteListS = new DefaultComboBoxModel<>();
		DefaultComboBoxModel<String> minuteListE = new DefaultComboBoxModel<>();
		JList listWithMinuteS = new JList(minuteListS);
		JList listWithMinuteE = new JList(minuteListE);
		for (int i = 0; i < 60 ; i++) {
			minuteListS.addElement(String.format("%02d", i));
			minuteListE.addElement(String.format("%02d", i));
		}
		
		int result = -1;
		JComboBox assignedTo = new JComboBox(usersList);
	    JComboBox dayStart = new JComboBox(dayListS);
	    JComboBox monthStart = new JComboBox(monthListS);
	    JComboBox yearStart = new JComboBox(yearListS);
	    JComboBox hourStart = new JComboBox(hourListS);
	    JComboBox minuteStart = new JComboBox(minuteListS);
	    JComboBox dayEnd = new JComboBox(dayListE);
	    JComboBox monthEnd = new JComboBox(monthListE);
	    JComboBox yearEnd = new JComboBox(yearListE);
	    JComboBox hourEnd = new JComboBox(hourListE);
	    JComboBox minuteEnd = new JComboBox(minuteListE);
		JTextField start = new JTextField(30);
	    JTextField end = new JTextField(30);
		JTextField taskName = new JTextField(25);
	    JTextField taskDescription = new JTextField(30);
	    JCheckBox repeatableEachYear = new JCheckBox("Repeatable yearly");
	    JCheckBox repeatableMonthly = new JCheckBox("Repeatable monthly");
	    JList listWithCellTasks = new JList(cellTasks);
	    JPanel taskPanel = new JPanel();
	    JPanel datePanelStart = new JPanel();
	    JPanel datePanelEnd = new JPanel();

		start.setEditable(false);
		end.setEditable(false);

		// create the panels with the JComboBoxes (start and end date)
		datePanelStart.add(yearStart);
		datePanelStart.add(monthStart);
		datePanelStart.add(dayStart);
		datePanelStart.add(hourStart);
		datePanelStart.add(minuteStart);
				
		datePanelEnd.add(yearEnd);
		datePanelEnd.add(monthEnd);
		datePanelEnd.add(dayEnd);
		datePanelEnd.add(hourEnd);
		datePanelEnd.add(minuteEnd);
				
		//create the panel for tasks
		taskPanel.add(new JLabel("Name of task:"));
		taskPanel.add(taskName);
		taskPanel.add(new JLabel("Description of task:"));
		taskPanel.add(taskDescription);
		taskPanel.add(new JLabel("Choose the user:"));
		taskPanel.add(assignedTo);
		taskPanel.add(new JLabel("Start time: ")); 
		taskPanel.add(start); 
		taskPanel.add(datePanelStart);
		taskPanel.add(new JLabel("End time: "));
		taskPanel.add(end); 
		taskPanel.add(datePanelEnd);
		taskPanel.add(repeatableEachYear);
		taskPanel.add(repeatableMonthly);
		taskPanel.add(Box.createVerticalStrut(15));
		taskPanel.add(new JLabel("Today's tasks: "));
		taskPanel.add(listWithCellTasks); // problem with today's date
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		
		// listeners for JComboBoxes- start time
		yearStart.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					start.setText(yearStart.getSelectedItem().toString() + "-" + monthStart.getSelectedItem().toString() + "-" + 
							dayStart.getSelectedItem().toString() + " " + hourStart.getSelectedItem().toString() + ":" + minuteStart.getSelectedItem().toString() + ":00");
				}
			});
		monthStart.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					start.setText(yearStart.getSelectedItem().toString() + "-" + monthStart.getSelectedItem().toString() + "-" + 
							dayStart.getSelectedItem().toString() + " " + hourStart.getSelectedItem().toString() + ":" + minuteStart.getSelectedItem().toString() + ":00");
				}
			});
		dayStart.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					start.setText(yearStart.getSelectedItem().toString() + "-" + monthStart.getSelectedItem().toString() + "-" + 
							dayStart.getSelectedItem().toString() + " " + hourStart.getSelectedItem().toString() + ":" + minuteStart.getSelectedItem().toString() + ":00");
				}
			});
		hourStart.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					start.setText(yearStart.getSelectedItem().toString() + "-" + monthStart.getSelectedItem().toString() + "-" + 
							dayStart.getSelectedItem().toString() + " " + hourStart.getSelectedItem().toString() + ":" + minuteStart.getSelectedItem().toString() + ":00");
				}	
			});
		minuteStart.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					start.setText(yearStart.getSelectedItem().toString() + "-" + monthStart.getSelectedItem().toString() + "-" + 
							dayStart.getSelectedItem().toString() + " " + hourStart.getSelectedItem().toString() + ":" + minuteStart.getSelectedItem().toString() + ":00");
				}
			});
				    
		// listeners for JComboBoxes- end time
		yearEnd.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					end.setText(yearEnd.getSelectedItem().toString() + "-" + monthEnd.getSelectedItem().toString() + "-" + 
							dayEnd.getSelectedItem().toString() + " " + hourEnd.getSelectedItem().toString() + ":" + minuteEnd.getSelectedItem().toString() + ":00");
				}
			});
		monthEnd.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					end.setText(yearEnd.getSelectedItem().toString() + "-" + monthEnd.getSelectedItem().toString() + "-" + 
							dayEnd.getSelectedItem().toString() + " " + hourEnd.getSelectedItem().toString() + ":" + minuteEnd.getSelectedItem().toString() + ":00");
				}
			});
		dayEnd.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					end.setText(yearEnd.getSelectedItem().toString() + "-" + monthEnd.getSelectedItem().toString() + "-" + 
							dayEnd.getSelectedItem().toString() + " " + hourEnd.getSelectedItem().toString() + ":" + minuteEnd.getSelectedItem().toString() + ":00");
				}
			});
		hourEnd.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					end.setText(yearEnd.getSelectedItem().toString() + "-" + monthEnd.getSelectedItem().toString() + "-" + 
							dayEnd.getSelectedItem().toString() + " " + hourEnd.getSelectedItem().toString() + ":" + minuteEnd.getSelectedItem().toString() + ":00");
				}	
			});
		minuteEnd.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					end.setText(yearEnd.getSelectedItem().toString() + "-" + monthEnd.getSelectedItem().toString() + "-" + 
							dayEnd.getSelectedItem().toString() + " " + hourEnd.getSelectedItem().toString() + ":" + minuteEnd.getSelectedItem().toString() + ":00");
				}	
			});
				
		if (!e.getValueIsAdjusting()) {
			Integer selectedData = null;
			int selectedRow = calendar.getSelectedRow();
			int selectedColumn = calendar.getSelectedColumn();
			if (selectedRow != -1 && selectedColumn != -1) {
        		selectedData = (Integer) calendar.getValueAt(selectedRow, selectedColumn);
        	    int month = cal.get(Calendar.MONTH);
        		int year = cal.get(Calendar.YEAR);
        		System.out.println(selectedData);
        			if (selectedData != null) {
        				Date cellDate = new GregorianCalendar(year, month, selectedData).getTime();
        				Calendar cellCalendar = Calendar.getInstance(); 
        				cellCalendar.setTime(cellDate); 
        				for (Task t : tasks) {
        					Date firstDate = CellRenderer.getZeroTimeDate(t.getStartTime());
        					Date secondDate = CellRenderer.getZeroTimeDate(cellDate);
        					if (firstDate.equals(secondDate)) {
        						System.out.println("Today's task");
        						cellTasks.addElement(t);
        					}
        				}
        				
        				// JComboBoxes for choose the date- start with choosing date
        				yearStart.setSelectedItem(cellCalendar.get(Calendar.YEAR));
        				monthStart.setSelectedIndex(cellCalendar.get(Calendar.MONTH));
        				dayStart.setSelectedIndex(cellCalendar.get(Calendar.DAY_OF_MONTH) - 1);
        				hourStart.setSelectedIndex(cellCalendar.get(Calendar.HOUR_OF_DAY));
        				minuteStart.setSelectedIndex(cellCalendar.get(Calendar.MINUTE));
        				yearEnd.setSelectedItem(cellCalendar.get(Calendar.YEAR));
        				monthEnd.setSelectedIndex(cellCalendar.get(Calendar.MONTH));
        				dayEnd.setSelectedIndex(cellCalendar.get(Calendar.DAY_OF_MONTH) - 1);
        				hourEnd.setSelectedIndex(cellCalendar.get(Calendar.HOUR_OF_DAY));
        				minuteEnd.setSelectedIndex(cellCalendar.get(Calendar.MINUTE));
        		
        				// display the date from JComboBoxes in the JTextFields
        				start.setText(yearStart.getSelectedItem().toString() + "-" + monthStart.getSelectedItem().toString() + "-" + 
        						dayStart.getSelectedItem().toString() + " " + hourStart.getSelectedItem().toString() + ":" + minuteStart.getSelectedItem().toString() + ":00");		    
        				end.setText(yearEnd.getSelectedItem().toString() + "-" + monthEnd.getSelectedItem().toString() + "-" + 
        						dayEnd.getSelectedItem().toString() + " " + hourEnd.getSelectedItem().toString() + ":" + minuteEnd.getSelectedItem().toString() + ":00");
        		
        				result = JOptionPane.showConfirmDialog(null, taskPanel, 
        						"Create a new Task", JOptionPane.OK_CANCEL_OPTION);
        				if (result == JOptionPane.OK_OPTION) {
        					Task newTask = new Task(taskName.getText(), taskDescription.getText());
        					newTask.setStartTime(start.getText());
        					newTask.setEndTime(end.getText());
        					newTask.setAssignedTo((User)assignedTo.getSelectedItem());
        		
        					Task taskCreated = TaskDAO.insertTask(newTask, users);
        					tasks.add(taskCreated);
        			
        					Date today = Calendar.getInstance().getTime();
        					Date firstDate = CellRenderer.getZeroTimeDate(taskCreated.getStartTime());
        					Date secondDate = CellRenderer.getZeroTimeDate(today);
        					if (firstDate.equals(secondDate)) {
        						cellTasks.addElement(taskCreated);
        						tasksToday.addElement(taskCreated);
        					}
        					tasksList.addElement(taskCreated);
        					
        					Date tomorrow = new Date();
        					Calendar calTomorrow = Calendar.getInstance();
        					calTomorrow.setTime(tomorrow);
        					calTomorrow.add(Calendar.DATE, 1);
        					tomorrow = calTomorrow.getTime();
        					Date tomorrowDate = CellRenderer.getZeroTimeDate(tomorrow);
        					if (firstDate.equals(tomorrowDate)) {
        						tasksTomorrow.addElement(taskCreated);
        					}
        					
        					// save tasks repeatable yearly
        					if (repeatableEachYear.isSelected()) {
        						int yearRepeatable = taskCreated.getStartTime().getYear();
        						for (int i = 1; i <= 10; i++) {
        							Task t = new Task(taskName.getText(), taskDescription.getText()); 
        							Date startDate = taskCreated.getStartTime();
        							startDate.setYear(yearRepeatable + i);
        							t.setStartTime(startDate); 
        							Date endDate = taskCreated.getEndTime();
        							endDate.setYear(yearRepeatable + i);
        							t.setEndTime(endDate); 	
        							t.setAssignedTo(taskCreated.getAssignedTo());
        							TaskDAO.insertTask(t, users); 
        						}
        					}
        		         
        					// save tasks repeatable monthly
        					if (repeatableMonthly.isSelected()) {
        						int monthRepeatable = taskCreated.getStartTime().getMonth();
        						for (int i = 1; i <= 12 - monthRepeatable; i++) {
        							Task t = new Task(taskName.getText(), taskDescription.getText()); 
        							Date startDate = taskCreated.getStartTime();
        							startDate.setMonth(monthRepeatable + i);
        							t.setStartTime(startDate); 
        							Date endDate = taskCreated.getEndTime();
        							endDate.setMonth(monthRepeatable + i);
        							t.setEndTime(endDate); 	
        							t.setAssignedTo(taskCreated.getAssignedTo());
        							TaskDAO.insertTask(t, users); 
        						}
        					}
        					cellTasks.removeAllElements();
        				}
        				if (result == JOptionPane.CANCEL_OPTION) {
        					cellTasks.removeAllElements();
        				}
        			}
        			System.out.println("Selected: " + selectedData);
			}
		}
	}};