
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

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
				} else {
					if ((gridX == -1) || (gridY == -1)) {
						//Is releasing outside
						//Do nothing
					} else {
						if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
							//Released the mouse button on a different cell where it was pressed
							//Do nothing
						} else {

							//Released the mouse button on the same cell where it was pressed
							if ((gridX == 0) || (gridY == 0)) {
								//If the mouse left button is released on the left row
								Color newColor = null;
								if (gridX == 0 && gridY > 0){

									for (int i=1; i<10; i++){
										switch (generator.nextInt(5)) {
										case 0:
											newColor = Color.YELLOW;
											break;
										case 1:
											newColor = Color.MAGENTA;
											break;
										case 2:
											newColor = Color.BLACK;
											break;
										case 3:
											newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										case 4:
											newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										}


										myPanel.colorArray[i][gridY] = newColor;
										myPanel.repaint();


									}


								}
								//If the left mouse button is released on the top row
								else if (gridX > 0 && gridY == 0 ){
									for (int i=1; i<10; i++){
										switch (generator.nextInt(5)) {
										case 0:
											newColor = Color.YELLOW;
											break;
										case 1:
											newColor = Color.MAGENTA;
											break;
										case 2:
											newColor = Color.BLACK;
											break;
										case 3:
											newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										case 4:
											newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										}


										myPanel.colorArray[gridX][i] = newColor;
										myPanel.repaint();


									}


								}
								//If the top left cell is pressed
								else if(gridX ==0 && gridY ==0) {
									for (int i=1; i<10;i++){
										for (int j=1; j<10;j++){
											if (i==j){
												switch (generator.nextInt(5)) {
												case 0:
													newColor = Color.YELLOW;
													break;
												case 1:
													newColor = Color.MAGENTA;
													break;
												case 2:
													newColor = Color.BLACK;
													break;
												case 3:
													newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
													break;
												case 4:
													newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
													break;
												}
												myPanel.colorArray[i][j] = newColor;
												myPanel.repaint();
											}                                    

										}
									}

								}
								// If the bottom left cell is pressed
								if (gridX==0 && gridY == 10) {                                
									for (int i=4; i<7; i++){
										for (int j=4; j<7; j++){
											switch (generator.nextInt(5)) {
											case 0:
												newColor = Color.YELLOW;
												break;
											case 1:
												newColor = Color.MAGENTA;
												break;
											case 2:
												newColor = Color.BLACK;
												break;
											case 3:
												newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
												break;
											case 4:
												newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
												break;
											}
											myPanel.colorArray[i][j] = newColor;
											myPanel.repaint();

										}
									}
								}

								//On the left column and on the top row... do nothing
							} else {
								Color gridColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];                            //On the grid other than on the left column and on the top row:
								Color newColor = null;
								//The grids that are not from the top row or the left row.
								do {

									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}

								}
								while(gridColor.equals(newColor));


								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							}
						}
					}
				}
				myPanel.repaint();
				break;
			case 3:        //Right mouse button
				
				Color newALLColor = null;
				
				if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {

					for(int i=1; i<10; i++){

						for(int j=1; j<10; j++){

							switch (generator.nextInt(3)) {				

				
				case 0:
	
							
					newALLColor = Color.RED;

					break;

				case 1:

					newALLColor = Color.ORANGE;

					break;

				case 2:

					newALLColor = Color.DARK_GRAY;

					break;
				}
					
					myPanel.colorArray[i][j] = newALLColor;
					myPanel.repaint();		

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