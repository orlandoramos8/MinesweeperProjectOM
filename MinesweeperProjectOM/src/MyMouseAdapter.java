import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {
    private Random generator = new Random();

 
    
    public void mousePressed(MouseEvent e) {

        Component c = e.getComponent();
        while (!(c instanceof JFrame)) {
            c = c.getParent();
            if (c == null) {
                return;
            }
        }
        JFrame myFrame = (JFrame) c;
        MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
        Insets myInsets = myFrame.getInsets();
        int x1 = myInsets.left;
        int y1 = myInsets.top;
        e.translatePoint(-x1, -y1);
        int x = e.getX();
        int y = e.getY();
        myPanel.x = x;
        myPanel.y = y;
        myPanel.mouseDownGridX = myPanel.getGridX(x, y);
        myPanel.mouseDownGridY = myPanel.getGridY(x, y);
        myPanel.repaint();


        switch (e.getButton()) {
        case 1:     //Left mouse button
        	
        	
        break;       
        case 3:        //Right mouse button      


            break;
        default:    //Some other button (2 = Middle mouse button, etc.)
            //Do nothing
            break;
        }
    }
    
    
    @SuppressWarnings("unused")
    public void mouseReleased(MouseEvent e) {

        Component c = e.getComponent();
        while (!(c instanceof JFrame)) {
            c = c.getParent();
            if (c == null) {
                return;
            }
        }
        JFrame myFrame = (JFrame)c;
        MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
        Insets myInsets = myFrame.getInsets();
        int x1 = myInsets.left;
        int y1 = myInsets.top;
        e.translatePoint(-x1, -y1);
        int x = e.getX();
        int y = e.getY();
        myPanel.x = x;
        myPanel.y = y;
        int gridX = myPanel.getGridX(x, y);
        int gridY = myPanel.getGridY(x, y);

        switch (e.getButton()) {
        
        
        case 1:        //Left mouse button

            if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
                //Had pressed outside
                //Do nothing
            } 
            else {
                if ((gridX == -1) || (gridY == -1)) {
                    //Is releasing outside
                    //Do nothing
                } else {
                    if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
                        //Released the mouse button on a different cell where it was pressed
                        //Do nothing
                    } else {

                        //Released the mouse button on the same cell where it was pressed
                        if ((gridX >= 0) || (gridY >= 0)) {
                        	
                        	
                        	boolean dead=false;
                        	for(int i= 0 ; i<myPanel.getListMines().size(); i++){
                            	Mines m=myPanel.getListMines().get(i);
                            	if(m.getGridX()==gridX && m.getGridY()==gridY){
                            		dead=true;break;
                            	}
                            	System.out.println(m.getGridX()+":"+m.getGridY());
                            }
                        	
                        	if(dead){
                        		//game over
                        		Color newColor = Color.BLACK;
                                myPanel.colorArray[gridX][gridY] = newColor;
                                for(int i= 0 ; i<myPanel.getListMines().size(); i++){
                                	Mines m=myPanel.getListMines().get(i);
                                	 myPanel.colorArray[m.getGridX()][m.getGridY()] = newColor;
                                }
                                
                                myPanel.repaint();
                                
                                JOptionPane.showMessageDialog(myFrame,"Game Over");
                        		
                        	}else{
                        		//continue
                        		Color newColor = Color.LIGHT_GRAY;
                                myPanel.colorArray[gridX][gridY] = newColor;
                                
                                //buscar Minas cercanas
                                
                                
                                int countMinesP1=0,countMinesP2=0,countMinesP3=0,countMinesP4=0,countMinesP5=0,countMinesP6=0,
                                	countMinesP7=0, countMinesP8=0;
                                for(int i= 0 ; i<myPanel.getListMines().size(); i++){
                                	//Punto 1(x-1;y-1)
                                	Mines m=myPanel.getListMines().get(i);
                                	 if(m.getGridX()==gridX-2 && m.getGridY()==gridY){
                                		 countMinesP1++;
                                	 }
                                	 if(m.getGridX()==gridX-2 && m.getGridY()==gridY-1){
                                		 countMinesP1++;
                                	 }
                                	 if(m.getGridX()==gridX-2 && m.getGridY()==gridY-2){
                                		 countMinesP1++;
                                	 }
                                	 if(m.getGridX()==gridX-1 && m.getGridY()==gridY-2){
                                		 countMinesP1++;
                                	 }
                                	 if(m.getGridX()==gridX && m.getGridY()==gridY-2){
                                		 countMinesP1++;
                                	 }
                                	 if(m.getGridX()==gridX-1 && m.getGridY()==gridY){
                                		 countMinesP1++;
                                	 }
                                	 if(m.getGridX()==gridX && m.getGridY()==gridY-1){
                                		 countMinesP1++;
                                	 }
                                	 
                                	 //Punto2
                                	 if(m.getGridX()==gridX-1 && m.getGridY()==gridY){
                                		 countMinesP2++;
                                	 }
                                	 if(m.getGridX()==gridX-1 && m.getGridY()==gridY-1){
                                		 countMinesP2++;
                                	 }
                                	 if(m.getGridX()==gridX-1 && m.getGridY()==gridY-2){
                                		 countMinesP2++;
                                	 }
                                	 if(m.getGridX()==gridX && m.getGridY()==gridY-2){
                                		 countMinesP2++;
                                	 }
                                	 if(m.getGridX()==gridX+1 && m.getGridY()==gridY-2){
                                		 countMinesP2++;
                                	 }
                                	 if(m.getGridX()==gridX+1 && m.getGridY()==gridY-1){
                                		 countMinesP2++;
                                	 }
                                	 if(m.getGridX()==gridX+1 && m.getGridY()==gridY){
                                		 countMinesP2++;
                                	 }
                                	 
                                	 
                                	 //Punto3
                                	 
                                	 
                                	 
                                	 
                                }
                                
                                myPanel.setLayout( null );
                                
                                if(countMinesP1>0){
                                	JLabel label11 = new JLabel("1");
                                    JLabel label12 = new JLabel("2");
                                    JLabel label13 = new JLabel("3");
                                    JLabel label14 = new JLabel("4");
                                    JLabel label15 = new JLabel("5");
                                    JLabel label16 = new JLabel("6");
                                    JLabel label17 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP1){
                                		case 1: 
                                				label11.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                                				label11.setVisible(true); 
                                				myPanel.add(label11);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label12.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                                				label12.setVisible(true);
                                				myPanel.add(label12);break;
                                		case 3: label13.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                                				label13.setVisible(true);
                                				myPanel.add(label13);break;
                                		case 4: label14.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                                				label14.setVisible(true);
                                				myPanel.add(label14);break;
                                		case 5: label15.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                                				label15.setVisible(true);
                                				myPanel.add(label15);break;
                                		case 6: label16.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                        						label16.setVisible(true);
                        						myPanel.add(label16);break;
                                		case 7: label17.setBounds(13+29*gridX,y1+29*gridY, 29,29  );
                        						label17.setVisible(true);
                        						myPanel.add(label17);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P1: "+countMinesP1);
                                	
                                      
                                	
                                }
                                
                                if(countMinesP2>0){
                                	JLabel label21 = new JLabel("1");
                                    JLabel label22 = new JLabel("2");
                                    JLabel label23 = new JLabel("3");
                                    JLabel label24 = new JLabel("4");
                                    JLabel label25 = new JLabel("5");
                                    JLabel label26 = new JLabel("6");
                                    JLabel label27 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP2){
                                		case 1: 
                                				label21.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label21.setVisible(true); 
                                				myPanel.add(label21);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label22.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label22.setVisible(true);
                                				myPanel.add(label22);break;
                                		case 3: label23.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label23.setVisible(true);
                                				myPanel.add(label23);break;
                                		case 4: label24.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label24.setVisible(true);
                                				myPanel.add(label24);break;
                                		case 5: label25.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label25.setVisible(true);
                                				myPanel.add(label25);break;
                                		case 6: label26.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label26.setVisible(true);
                        						myPanel.add(label26);break;
                                		case 7: label27.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label27.setVisible(true);
                        						myPanel.add(label27);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P2: "+countMinesP2);
                                	
                                }
                                
                                if(countMinesP3>0){
                                	//Colocar el numero de Minas

                                	JLabel label28 = new JLabel("1");
                                    JLabel label29 = new JLabel("2");
                                    JLabel label30 = new JLabel("3");
                                    JLabel label31 = new JLabel("4");
                                    JLabel label32 = new JLabel("5");
                                    JLabel label33 = new JLabel("6");
                                    JLabel label34 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP2){
                                		case 1: 
                                				label28.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label28.setVisible(true); 
                                				myPanel.add(label28);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label29.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label29.setVisible(true);
                                				myPanel.add(label29);break;
                                		case 3: label30.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label30.setVisible(true);
                                				myPanel.add(label30);break;
                                		case 4: label31.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label31.setVisible(true);
                                				myPanel.add(label31);break;
                                		case 5: label32.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label32.setVisible(true);
                                				myPanel.add(label32);break;
                                		case 6: label33.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label33.setVisible(true);
                        						myPanel.add(label33);break;
                                		case 7: label34.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label34.setVisible(true);
                        						myPanel.add(label34);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P2: "+countMinesP2);
                                	
                                }
                                if(countMinesP4>0){
                                	//Colocar el numero de Minas

                                	JLabel label35 = new JLabel("1");
                                    JLabel label36 = new JLabel("2");
                                    JLabel label37 = new JLabel("3");
                                    JLabel label38 = new JLabel("4");
                                    JLabel label39 = new JLabel("5");
                                    JLabel label40 = new JLabel("6");
                                    JLabel label41 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP2){
                                		case 1: 
                                				label35.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label35.setVisible(true); 
                                				myPanel.add(label35);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label36.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label36.setVisible(true);
                                				myPanel.add(label36);break;
                                		case 3: label37.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label37.setVisible(true);
                                				myPanel.add(label37);break;
                                		case 4: label38.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label38.setVisible(true);
                                				myPanel.add(label38);break;
                                		case 5: label39.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label39.setVisible(true);
                                				myPanel.add(label39);break;
                                		case 6: label40.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label40.setVisible(true);
                        						myPanel.add(label40);break;
                                		case 7: label41.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label41.setVisible(true);
                        						myPanel.add(label41);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P2: "+countMinesP2);
                                	
                                }
                                if(countMinesP5>0){
                                	//Colocar el numero de Minas

                                	JLabel label42 = new JLabel("1");
                                    JLabel label43 = new JLabel("2");
                                    JLabel label44 = new JLabel("3");
                                    JLabel label45 = new JLabel("4");
                                    JLabel label46 = new JLabel("5");
                                    JLabel label47 = new JLabel("6");
                                    JLabel label48 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP2){
                                		case 1: 
                                				label42.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label42.setVisible(true); 
                                				myPanel.add(label42);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label43.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label43.setVisible(true);
                                				myPanel.add(label44);break;
                                		case 3: label44.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label44.setVisible(true);
                                				myPanel.add(label45);break;
                                		case 4: label45.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label45.setVisible(true);
                                				myPanel.add(label45);break;
                                		case 5: label46.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label46.setVisible(true);
                                				myPanel.add(label46);break;
                                		case 6: label47.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label47.setVisible(true);
                        						myPanel.add(label47);break;
                                		case 7: label48.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label48.setVisible(true);
                        						myPanel.add(label48);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P2: "+countMinesP2);
                                	
                                }
                                if(countMinesP6>0){
                                	//Colocar el numero de Minas

                                	JLabel label49 = new JLabel("1");
                                    JLabel label50 = new JLabel("2");
                                    JLabel label51 = new JLabel("3");
                                    JLabel label52 = new JLabel("4");
                                    JLabel label53 = new JLabel("5");
                                    JLabel label54= new JLabel("6");
                                    JLabel label55 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP2){
                                		case 1: 
                                				label49.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label49.setVisible(true); 
                                				myPanel.add(label49);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label50.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label50.setVisible(true);
                                				myPanel.add(label50);break;
                                		case 3: label51.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label51.setVisible(true);
                                				myPanel.add(label51);break;
                                		case 4: label52.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label52.setVisible(true);
                                				myPanel.add(label52);break;
                                		case 5: label53.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label53.setVisible(true);
                                				myPanel.add(label53);break;
                                		case 6: label54.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label54.setVisible(true);
                        						myPanel.add(label54);break;
                                		case 7: label55.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label55.setVisible(true);
                        						myPanel.add(label55);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P2: "+countMinesP2);
                                	
                                }
                                if(countMinesP7>0){
                                	//Colocar el numero de Minas

                                	JLabel label21 = new JLabel("1");
                                    JLabel label22 = new JLabel("2");
                                    JLabel label23 = new JLabel("3");
                                    JLabel label24 = new JLabel("4");
                                    JLabel label25 = new JLabel("5");
                                    JLabel label26 = new JLabel("6");
                                    JLabel label27 = new JLabel("7");
                                	//Colocar el numero de Minas
                                	switch(countMinesP2){
                                		case 1: 
                                				label21.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label21.setVisible(true); 
                                				myPanel.add(label21);
                                				myFrame.add(myPanel);
                                				break;
                                		case 2: label22.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label22.setVisible(true);
                                				myPanel.add(label22);break;
                                		case 3: label23.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label23.setVisible(true);
                                				myPanel.add(label23);break;
                                		case 4: label24.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label24.setVisible(true);
                                				myPanel.add(label24);break;
                                		case 5: label25.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                                				label25.setVisible(true);
                                				myPanel.add(label25);break;
                                		case 6: label26.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label26.setVisible(true);
                        						myPanel.add(label26);break;
                                		case 7: label27.setBounds(42+29*(gridX),y1+29*gridY, 29,29  );
                        						label27.setVisible(true);
                        						myPanel.add(label27);break;
                                	}
                                	myPanel.repaint();
                                	
                                	System.out.println("P2: "+countMinesP2);
                                	
                                }
                                
                                
                                
                                myPanel.repaint();
                        	}
                        	
                           

                            }
                        }
                    }
                }
            break;

               


        case 3:        //Right mouse button
        	
        	
        	if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
                //Had pressed outside
                //Do nothing
            } 
            else {
                if ((gridX == -1) || (gridY == -1)) {
                    //Is releasing outside
                    //Do nothing
                } else {
                    if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
                        //Released the mouse button on a different cell where it was pressed
                        //Do nothing
                    } else {

                        //Released the mouse button on the same cell where it was pressed
                        if ((gridX >= 0) || (gridY >= 0)) {
                        	Color newALLColor = Color.RED;   
                            
                            myPanel.colorArray[gridX][gridY] = newALLColor;
                            myPanel.repaint();   

                            }
                        }
                    }
                }
           
            break;
        default:    //Some other button (2 = Middle mouse button, etc.)
            //Do nothing
            break;
        }
    }
}