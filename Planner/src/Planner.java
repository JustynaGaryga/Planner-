import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Planner extends JFrame {
	JFrame plannerFrame = new JFrame();
	JPanel listPanel = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JButton addUserButton = new JButton("Add new User");
	JButton addTaskButton = new JButton("Add new Task");
	JButton selectTaskButton = new JButton("Choose new task");
	JLabel userLabel = new JLabel (" USERS: ", SwingConstants.LEFT);
	JLabel taskLabel = new JLabel (" TASKS: ", SwingConstants.LEFT);
	
	DefaultListModel<User> usersList = new DefaultListModel<>();
	DefaultListModel<Task> tasksList = new DefaultListModel<>();
	JList listWithUsers = new JList(usersList);
	JList listWithTasks = new JList(tasksList);
	
	public Planner() {
		super ("Planner. Learn IT, Girl!");
		plannerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		plannerFrame.setSize(800, 600);
		plannerFrame.setLocation(200, 100);
		plannerFrame.getContentPane().setBackground(Color.yellow); // temporary color
		plannerFrame.setVisible(true);
		
		//click on button 
		addUserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JTextField userName = new JTextField(25);
			    JTextField userSurname = new JTextField(25);
			    JPanel userPanel = new JPanel();
			    userPanel.add(new JLabel("Name:"));
			    userPanel.add(userName);
			    userPanel.add(Box.createVerticalStrut(15)); // a spacer
			    userPanel.add(new JLabel("Surname:"));
			    userPanel.add(userSurname);
			    userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

			    int result = JOptionPane.showConfirmDialog(null, userPanel, 
			               "Create a new User", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			         System.out.println("Name: " + userName.getText());
			         System.out.println("Surname: " + userSurname.getText());
			         User newUser = new User(userName.getText(), userSurname.getText());
			         usersList.addElement(newUser);
			    }}});
		
		// click on button
		addTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JTextField taskName = new JTextField(25);
			    JTextField taskDescription = new JTextField(50);
			    JPanel taskPanel = new JPanel();
			    taskPanel.add(new JLabel("Name of task:"));
			    taskPanel.add(taskName);
			    taskPanel.add(Box.createVerticalStrut(15)); // a spacer
			    taskPanel.add(new JLabel("Description of task:"));
			    taskPanel.add(taskDescription);
			    taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

			    int result = JOptionPane.showConfirmDialog(null, taskPanel, 
			               "Create a new Task", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			         System.out.println("Name of task: " + taskName.getText());
			         System.out.println("Description of task: " + taskDescription.getText());
			         Task newTask = new Task(taskName.getText(), taskDescription.getText());
			         tasksList.addElement(newTask);
			    }}});
		
		selectTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String[] selectTaskList = {"Wash clothes", "Shopping"}; // temporary list
				String[] assignedToList = {"Justyna Garyga", "Piter Garyga"}; // temporary list
				JComboBox selectTask = new JComboBox(selectTaskList);
				JComboBox assignedTo = new JComboBox(assignedToList);
				JTextField start = new JTextField(50);
			    JTextField end = new JTextField(50);
			    JPanel selectPanel = new JPanel();
			    selectPanel.add(new JLabel("Choose the task:"));
			    selectPanel.add(selectTask);
			    selectPanel.add(Box.createVerticalStrut(15)); // a spacer
			    selectPanel.add(new JLabel("Choose the user:"));
			    selectPanel.add(assignedTo);
			    selectPanel.add(new JLabel("Start time: ")); 
			    selectPanel.add(start); // change to field where user can select the date and time
			    selectPanel.add(new JLabel("End time: "));
			    selectPanel.add(end); // change to field where user can select the date and time
			    selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));

			    int result = JOptionPane.showConfirmDialog(null, selectPanel, 
			               "Choose the task and user", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			    
			    }}});
		
		//add buttons to panelButtons
		buttonPanel.add(addUserButton);
	    buttonPanel.add(addTaskButton);
	    buttonPanel.setSize(200, 200);
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		// User's list
		User user1 = new User("Justyna", "Garyga");
		User user2 = new User("Piter", "Garyga");
		User user3 = new User("Kris", "Garyga");
		User user4 = new User("Barbara", "Garyga");
		
		usersList.addElement(user1);
		usersList.addElement(user2);
		usersList.addElement(user3);
		usersList.addElement(user4);
		
		// Task's list
		Task task1 = new Task("Wash clothes", "washing clothes");
		Task task2 = new Task("Shopping", "buying food and ");
		Task task3 = new Task("Wash a car", "go to a car wash ");
		Task task4 = new Task("Prepare breakfast", "at 6:00 AM");
		Task task5 = new Task("Prepare dinner", "at 16: PM");
		Task task6 = new Task("Visit my parents", "Majakowskiego Street");
		Task task7 = new Task("Visit Piter's parents", "Panewnicka Street");
	
		tasksList.addElement(task1);
		tasksList.addElement(task2);
		tasksList.addElement(task3);
		tasksList.addElement(task4);
		tasksList.addElement(task5);
		tasksList.addElement(task6);
		tasksList.addElement(task7);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		userLabel.setBorder(border); 
		taskLabel.setBorder(border);
		
		listPanel.add(userLabel); 
		listPanel.add(listWithUsers);
		listPanel.add(Box.createRigidArea(new Dimension(0,20)));
		listPanel.add(taskLabel);
		listPanel.add(listWithTasks);
		listPanel.setBackground(Color.white);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		listPanel.setAlignmentY(LEFT_ALIGNMENT); // correct alignment to the left (labels!) 
		
		// layout
		topPanel.setLayout(new BorderLayout());
		topPanel.setBackground(Color.white);
		topPanel.add(buttonPanel, BorderLayout.LINE_END);
		topPanel.add(selectTaskButton, BorderLayout.LINE_START);
		plannerFrame.add(topPanel, BorderLayout.PAGE_START);
		plannerFrame.add(listPanel, BorderLayout.LINE_START);
	}      
}
