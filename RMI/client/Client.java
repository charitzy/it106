import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class Client extends JFrame{

    public static void main(String[] args){
        //Panels
        ViewStudentPanel studentList = new ViewStudentPanel();
        JScrollPane scroll = new JScrollPane(studentList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //Navigation
        JPanel NavBar = new JPanel();
        NavBar.setBounds(0, 0, 1000, 90);
        NavBar.setBackground(new Color(255, 153, 0));
        NavBar.setVisible(true);
        //Navigation Buttons
        JButton Refresh = new JButton("Refresh");
        JButton Sort = new JButton("Sort");
        JButton Extract = new JButton("Extract XML");

        Refresh.setFont(new java.awt.Font("Segoe UI Black",1 ,24));
        Refresh.setBounds(130, 30, 100,20);
        Refresh.setBackground(new Color(255, 153, 0));
        Refresh.setFocusable(false);
        Refresh.setBorder(null);

        Sort.setFont(new java.awt.Font("Segoe UI Black",1 ,24));
        Sort.setBounds(430, 30, 100,20);
        Sort.setBackground(new Color(255, 153, 0));
        Sort.setFocusable(false);
        Sort.setBorder(null);

        Extract.setFont(new java.awt.Font("Segoe UI Black",1 ,24));
        Extract.setBounds(650, 30, 300,20);
        Extract.setBackground(new Color(255, 153, 0));
        Extract.setFocusable(false);
        Extract.setBorder(null);
        
        NavBar.add(Refresh);
        NavBar.add(Sort);
        NavBar.add(Extract);
        NavBar.setLayout(null);


        // main Frame 
        Client mainFrame = new Client();
        mainFrame.setSize(1000, 700);
        mainFrame.setTitle("Student Client");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setLocationRelativeTo(null);
        //scrollbar_part
        studentList.setLayout(new BoxLayout(studentList,BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(studentList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Add the scroll pane to the frame
        mainFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        NavBar.setVisible(true);
        studentList.setVisible(true);

        mainFrame.add(NavBar);
        mainFrame.add(studentList);

        mainFrame.setVisible(true);

        
        

        //Actions 
        Refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                studentList.fetch("Select * from student");
            }
            
        });

        Extract.addActionListener(new ActionListener() {

            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File xmlFile = new File("xml/StudentList.xml");
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc = builder.parse(xmlFile);
                    doc.getDocumentElement().normalize();


                    NodeList list = doc.getElementsByTagName("Student");

                    for(int i = 0; i < list.getLength(); i++){
                        Node nNode =  list.item(i);

                        if(nNode.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) nNode;
                            Student xmlStudent = new Student();
                            System.out.println("Student ID: " + element.getAttribute("id"));
                            System.out.println("    Student Name: " + element.getElementsByTagName("name").item(0).getTextContent());
                            System.out.printlnN
                                c.printStackTrace();
                            }
                    
                        }
                        
                    }
                    System.out.println("XML Extracting done.........");
                    
                    
                } catch (Exception b) {
                    
                    b.printStackTrace();
                }
                studentList.fetch("Select * from student");

            }
            
        });""



        Sort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame sortPanel = new JFrame("Sort By");
                sortPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sortPanel.setVisible(true);
                sortPanel.setLocationRelativeTo(null);
                sortPanel.setSize(400, 300);
                sortPanel.setResizable(false);
                sortPanel.setLayout(new GridLayout(2,0));

                JButton sortByName = new JButton("Sort By Name");
                JButton sortByAge = new JButton("Sort By Age");

                sortPanel.add(sortByName);
                sortPanel.add(sortByAge);

                sortByAge.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        studentList.fetch("Select * from student Order by AGE");
                        sortPanel.dispose();
                    }
                    
                });

                sortByName.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        studentList.fetch("Select * from student Order by NAME");
                        sortPanel.dispose();
                    }
                    
                });





            }
            
        });
}