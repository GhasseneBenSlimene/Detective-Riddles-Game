
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class EnJeu implements ActionListener{
	
	ArrayList<String> Questions = new ArrayList<String>();
	ArrayList<String> Reponces= new ArrayList<String>();
	ArrayList<JTextField> reponceEntree=new ArrayList<JTextField>();

	int index;
	int seconds=60;
	
	Joueur j1,j2;
	
	JButton buttonNext = new JButton();
	JButton rejouer = new JButton();
	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JTextArea textarea = new JTextArea();
	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField Joueur1 = new JTextField();
	JTextField points1 = new JTextField();
	JTextField Joueur2 = new JTextField();
	JTextField points2 = new JTextField();
	
	//compte à rebours au cours de chaque question
	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				seconds--;
				seconds_left.setText(String.valueOf(seconds));
				if(seconds<=0) {
					buttonNext.doClick();
				}
			}
		});
	
	
	
	//creation des élément de l'interface graphique 
	//non variant au cours de jeu
	public EnJeu(Joueur j1,Joueur j2, int index) {
		try
	    {
		  this.j1=j1;
		  this.j2=j2;
		  this.index=index;
	      // Le fichier d'entrée
	      File file = new File("Q_R.txt");    
	      // Créer l'objet File Reader
	      FileReader fr = new FileReader(file);  
	      // Créer l'objet BufferedReader        
	      BufferedReader br = new BufferedReader(fr);
	      String line;
	      while((line = br.readLine()) != null)
	      { 
	        Questions.add(line);
	        line=br.readLine();
	        Reponces.add(line); 
	      }
	      fr.close();
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
		
		frame.setTitle("Jeu de mystères");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1100,650);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(false);
		
		textfield.setBounds(0,0,1100,50);
		textfield.setBackground(new Color(25,25,25));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("Ink Free",Font.BOLD,30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);
		
		textarea.setBounds(0,50,1100,100);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25,25,25));
		textarea.setForeground(new Color(25,255,0));
		textarea.setFont(new Font("MV Boli",Font.BOLD,25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);
		
		seconds_left.setBounds(535,510,100,100);
		seconds_left.setBackground(new Color(25,25,25));
		seconds_left.setForeground(new Color(255,0,0));
		seconds_left.setFont(new Font("Ink Free",Font.BOLD,60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		
		Joueur1.setBounds(225,225,200,100);
		Joueur1.setBackground(new Color(25,25,25));
		Joueur1.setForeground(new Color(25,255,0));
		Joueur1.setFont(new Font("Ink Free",Font.BOLD,40));
		Joueur1.setBorder(BorderFactory.createBevelBorder(1));
		Joueur1.setHorizontalAlignment(JTextField.CENTER);
		Joueur1.setEditable(false);
		
		points1.setBounds(225,325,200,100);
		points1.setBackground(new Color(25,25,25));
		points1.setFont(new Font("Ink Free",Font.BOLD,50));
		points1.setBorder(BorderFactory.createBevelBorder(1));
		points1.setHorizontalAlignment(JTextField.CENTER);
		points1.setEditable(false);
		
		Joueur2.setBounds(675,225,200,100);
		Joueur2.setBackground(new Color(25,25,25));
		Joueur2.setForeground(new Color(25,255,0));
		Joueur2.setFont(new Font("Ink Free",Font.BOLD,40));
		Joueur2.setBorder(BorderFactory.createBevelBorder(1));
		Joueur2.setHorizontalAlignment(JTextField.CENTER);
		Joueur2.setEditable(false);
		
		points2.setBounds(675,325,200,100);
		points2.setBackground(new Color(25,25,25));
		points2.setForeground(new Color(25,255,0));
		points2.setFont(new Font("Ink Free",Font.BOLD,50));
		points2.setBorder(BorderFactory.createBevelBorder(1));
		points2.setHorizontalAlignment(JTextField.CENTER);
		points2.setEditable(false);
		
		buttonNext.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
		buttonNext.setText("Valider");
		buttonNext.setForeground(new Color(225,225,225));
		buttonNext.setBackground(new Color(25,25,25));
		buttonNext.setBounds(525,400,120,50);
		buttonNext.addActionListener(this);
		buttonNext.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					 buttonNext.doClick();
				 }
			}
		});
		
		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(textarea);
		frame.add(textfield);
		frame.add(buttonNext);
		frame.setVisible(true);
		
		nextQuestion();
	}
	
	
	
	
	public void nextQuestion() {
		
		if(j1.getScore()>=2||j2.getScore()>=2) {
			results();
		}
		else {
			if(index%2==0) {
				textfield.setText(j1.getNom());
				if(j1.getColor()=="Rouge") {
					textfield.setForeground(Color.RED);
					textarea.setForeground(Color.RED);
				}
				else {
					textfield.setForeground(new Color(25,255,0));
					textarea.setForeground(new Color(25,255,0));
				}
			}
			else {
				textfield.setText(j2.getNom());
				if(j2.getColor()=="Vert"){
					textfield.setForeground(new Color(25,255,0));
					textarea.setForeground(new Color(25,255,0));
				}
				else{
					textfield.setForeground(Color.RED);
					textarea.setForeground(Color.RED);
				}
			}
			textarea.setText(Questions.get(index));
			ArrayList<JTextField> caraField = new ArrayList<JTextField>(Reponces.get(index).length());
			int Nb_case=Reponces.get(index).length();
			int x=0;
			//creation des zones d'ecriture selon le nombre des carateres de la reponce
			for(int i=0;i<Nb_case;i++) {
				int j=i;
				caraField.add(new JTextField());
				caraField.get(j).setFont(new Font("Ink Free", 1, 25));
				caraField.get(j).setHorizontalAlignment(JTextField.CENTER);
				caraField.get(j).setBounds(100+x,300,50,50);
				caraField.get(j).setBackground(Color.LIGHT_GRAY);
				x+=70;
				caraField.get(j).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	if(caraField.get(j).getText().equals("_"))
		            		caraField.get(j).setText("");
		              }
					public void focusLost(FocusEvent e) {
						caraField.get(j).setForeground(Color.MAGENTA);
						if(caraField.get(j).getText().isEmpty()) {
							caraField.get(j).setText("_");
							caraField.get(j).setForeground(Color.BLACK);
						}
	        		    if(caraField.get(j).getText().length()>1) {
	        			    String s=Character.toString(caraField.get(j).getText().charAt(1));
	        			    caraField.get(j).setText(s);
	        		    }
					}
		        });
				caraField.get(j).getDocument().addDocumentListener(new DocumentListener() {
		        	  public void changedUpdate(DocumentEvent e) {
		        	  }
		        	  public void removeUpdate(DocumentEvent e) {
		        	  }
		        	  public void insertUpdate(DocumentEvent e) {
		        		  if(!caraField.get(j).getText().equals("_"))
		        			  caraField.get(j).transferFocus();
		        	  }
		        });
				frame.add(caraField.get(j));
				caraField.get(j).setText("_");
			}
			
			reponceEntree=caraField;
			java.util.Random ran=new java.util.Random();
			if(Nb_case>12) {
				//generation des nombres random non égales
				int r1=ran.nextInt(Nb_case);
				int r2_g;
				do {
					r2_g=ran.nextInt(Nb_case);
				}while(r2_g==r1);
				int r3_g;
				do {
					r3_g=ran.nextInt(Nb_case);
				}while(r3_g==r2_g||r3_g==r1);
				int r2=r2_g;
				int r3=r3_g;
				
				//fixer des caractere predefinie
				caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
				caraField.get(r2).setText(Character.toString(Reponces.get(index).charAt(r2)));
				caraField.get(r3).setText(Character.toString(Reponces.get(index).charAt(r3)));
				caraField.get(r1).setEditable(false);
				caraField.get(r2).setEditable(false);
				caraField.get(r3).setEditable(false);
				caraField.get(r1).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
						caraField.get(r1).setForeground(Color.black);
		            }
					public void focusLost(FocusEvent e) {
						caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
						caraField.get(r1).setForeground(Color.black);
					}
		        });
				caraField.get(r2).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	caraField.get(r2).setText(Character.toString(Reponces.get(index).charAt(r2)));
						caraField.get(r2).setForeground(Color.black);
		            }
					public void focusLost(FocusEvent e) {
						caraField.get(r2).setText(Character.toString(Reponces.get(index).charAt(r2)));
						caraField.get(r2).setForeground(Color.black);
					}
		        });
				caraField.get(r3).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	caraField.get(r3).setText(Character.toString(Reponces.get(index).charAt(r3)));
						caraField.get(r3).setForeground(Color.black);
		            }
					public void focusLost(FocusEvent e) {
						caraField.get(r3).setText(Character.toString(Reponces.get(index).charAt(r3)));
						caraField.get(r3).setForeground(Color.black);
					}
		        });
			}else if(Nb_case<=12&&Nb_case>=9) {
				int r1=ran.nextInt(Nb_case);
				int r2=ran.nextInt(Nb_case);
				caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
				caraField.get(r2).setText(Character.toString(Reponces.get(index).charAt(r2)));
				caraField.get(r1).setEditable(false);
				caraField.get(r2).setEditable(false);
				caraField.get(r1).setRequestFocusEnabled(false);
				caraField.get(r2).setRequestFocusEnabled(false);
				caraField.get(r1).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
						caraField.get(r1).setForeground(Color.black);
		            }
					public void focusLost(FocusEvent e) {
						caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
						caraField.get(r1).setForeground(Color.black);
					}
		        });
				caraField.get(r2).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	caraField.get(r2).setText(Character.toString(Reponces.get(index).charAt(r2)));
						caraField.get(r2).setForeground(Color.black);
		            }
					public void focusLost(FocusEvent e) {
						caraField.get(r2).setText(Character.toString(Reponces.get(index).charAt(r2)));
						caraField.get(r2).setForeground(Color.black);
					}
		        });
			}else if(Nb_case<9) {
				int r1=ran.nextInt(Nb_case);
				caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
				caraField.get(r1).setEditable(false);
				caraField.get(r1).setRequestFocusEnabled(false);
				caraField.get(r1).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
		            	caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
		            }
					public void focusLost(FocusEvent e) {
						caraField.get(r1).setText(Character.toString(Reponces.get(index).charAt(r1)));
						caraField.get(r1).setForeground(Color.black);
					}
		        });
			}
			caraField.get(0).requestFocus();
			//le debut de compte a rebours
			timer.start();
		}
	}
	
	//la reaction de bouton valider lors de clique
	public void actionPerformed(ActionEvent e) {
		boolean Correct=false;
		int Nb_case=Reponces.get(index).length();
		String rep="";
		for(int i=0;i<Nb_case;i++) {
			if(reponceEntree.get(i).getText().isEmpty()) break;
			rep+=reponceEntree.get(i).getText().charAt(0);
		}
		if(Reponces.get(index).equalsIgnoreCase(rep)) {
			Correct=true;
			if(index%2==0) {
				j1.setScore(j1.getScore()+1);
			}
			else {
				j2.setScore(j2.getScore()+1);
			}
		}
		displayAnswer(Correct);
	}
	
	
	
	
	
	//monter les resultats
	public void displayAnswer(boolean Correct) {
		
		timer.stop();
		buttonNext.setEnabled(false);
		JLabel status=new JLabel();
		status.setBounds(525,220,150,50);
		status.setFont(new Font("Ink Free",Font.BOLD,30));
		if(Correct) {
			status.setText("Correcte !");
			status.setForeground(new Color(23,60,243));
		}
		else {
			status.setText("Incorrect");
			status.setForeground(new Color(233,6,52));
		}
		frame.add(status);
		status.setText(status.getText()+" ");
		int len=Reponces.get(index).length();
		for(int j=len-1;j>=0;j--) {
			int i=j;
			if(reponceEntree.get(i).isEditable()) {
				reponceEntree.get(i).setText(Character.toString(Reponces.get(index).charAt(i)));
				reponceEntree.get(i).setEditable(false);
				reponceEntree.get(i).setRequestFocusEnabled(false);
				reponceEntree.get(i).addFocusListener(new FocusListener() {
		            public void focusGained(FocusEvent e) {
						reponceEntree.get(i).setText(Character.toString(Reponces.get(index).charAt(i)));
		            }
					public void focusLost(FocusEvent e) {
		            	reponceEntree.get(i).setForeground(new Color(23,60,243));
					}
		        });
            	reponceEntree.get(i).setForeground(new Color(23,60,243));
			}
		}
		
		//pause apres l'affichage de resultat
		Timer pause = new Timer(3500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				for(JTextField JTF:reponceEntree) {
					JTF.hide();
					frame.remove(JTF);
				}
				
				frame.remove(status);
				
				seconds=60;
				seconds_left.setText(String.valueOf(seconds));
				buttonNext.setEnabled(true);
				index++;
				
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}
	
	
	
	
	//affichage de resultat final
	public void results(){
		
		buttonNext.hide();
		frame.remove(buttonNext);
		
		seconds_left.hide();
		seconds_left.remove(seconds_left);
		
		textfield.setText("Jeu termine");
		textfield.setForeground(new Color(23,60,243));
		textarea.setText("Bien jouer");
		textarea.setForeground(new Color(23,60,243));
		
		Joueur1.setText(j1.getNom());
		points1.setText(j1.getScore()+" Points");
		if(j1.getColor()=="Rouge") {
			Joueur1.setForeground(Color.RED);
			points1.setForeground(Color.RED);
		}
		else {
			Joueur1.setForeground(new Color(25,255,0));
			points1.setForeground(new Color(25,255,0));
		}
		
		Joueur2.setText(j2.getNom());
		points2.setText(j2.getScore()+" Points");
		
		
		if(j2.getColor()=="Rouge") {
			Joueur2.setForeground(Color.RED);
			points2.setForeground(Color.RED);
		}
		else {
			Joueur2.setForeground(new Color(25,255,0));
			points2.setForeground(new Color(25,255,0));
		}
		
		JLabel Gagnant=new JLabel();
		Gagnant.setForeground(new Color(23,60,243));
		Gagnant.setFont(new Font("Ink Free",Font.BOLD,40));
		
		if(j1.getScore()>j2.getScore()) {
			Gagnant.setBounds(230,450,200,80);
		}
		else {
			Gagnant.setBounds(680,450,200,80);
		}
		
		rejouer.setFont(new java.awt.Font("Ink Free", 1, 30));
		rejouer.setText("Rejouer");
		rejouer.setForeground(new Color(225,225,225));
		rejouer.setBackground(new Color(25,25,25));
		rejouer.setBounds(450,500,180,90);
		rejouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j1.setScore(0);
				j2.setScore(0);
				frame.hide();
				new EnJeu(j1,j2,index);
			}
		});
		
		frame.add(rejouer);
		
		frame.add(Joueur2);
		frame.add(points2);
		
		frame.add(Joueur1);
		frame.add(points1);
		
		frame.add(Gagnant);
		Gagnant.setText("Gagnant !");
	}
}