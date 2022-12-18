
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MatrixCalculator implements ActionListener 
{
    private static int col, row;  //dimensions
    private static double myMatrix [][];
    private static double tempMatrix [][]; 
    private static JTextField inputField [][];
	
    private static int result;
    private static JButton minusB, plusB, inverseB,
	multiplyB, nMultiplyB, nDivisionB, getValueB, 
	showMatrix, transposing, newMatrix;
	
    private static JPanel choosePanel [] = new JPanel[6];
    private static JFrame frame;
    private static int lastCol , lastRow;
	
	//Colors for Border and Background
	private static Color color = Color.MAGENTA.darker().darker().darker().darker();
	private static Color colorB = Color.LIGHT_GRAY;
	
    MatrixCalculator()
    {
        col = row = 0;
        myMatrix = new double [0][0];
        ChooseOperation();
    }
   
    //prompting for matrix's dimensions
    private static void getDimension() 
    {
      JTextField lField = new JTextField(5); //length field 
      JTextField wField = new JTextField(5); //col field
      
      //design input line
      JPanel choosePanel [] = new JPanel [2];
      choosePanel [0] = new JPanel();
      choosePanel [1] = new JPanel();
	  
      choosePanel[0].add(new JLabel("Enter Dimensions") );
      choosePanel[1].add(new JLabel("Rows:"));
      choosePanel[1].add(lField);
	  
      choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
      choosePanel[1].add(new JLabel("Cols:"));
      choosePanel[1].add(wField);
        
      result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null,JOptionPane.OK_CANCEL_OPTION, 
               JOptionPane.PLAIN_MESSAGE);
        
      //save last dimensions
      lastCol = col;
      lastRow = row;
      
      //ok option
		if(result == 0)
		{
			if(wField.getText().equals(""))
			{
				col = 0;
			}
			else
			{
				if(isInt(wField.getText()))
				{
					col = Integer.parseInt(wField.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong Dimensions");
					col = lastCol;
					row = lastRow;
					return;
				}
            
				if(isInt(lField.getText()))
				{
					row = Integer.parseInt(lField.getText());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong Dimensions");
					col = lastCol;
					row = lastRow;
					return;
				}
			}
		
			if(col < 1 || row < 1)
			{
				JOptionPane.showConfirmDialog(null, "You entered wrong dimensions", 
                  "Error",JOptionPane.PLAIN_MESSAGE);
				col  = lastCol;
				row = lastRow;
			}
			else
			{
				tempMatrix = myMatrix;
				myMatrix = new double [row][col];
				if(!setElements(myMatrix, "Fill your new matrix")) //filling the new matrix
				{
                //backup
                
					myMatrix = tempMatrix;
				}
			}
		}
		else if(result == 1)
		{
           col = lastCol;
           row = lastRow;
		}
    }//end get Dimension
	
    //setting a matrix's elements
    private static boolean setElements(double matrix [][], String title )
    {
        int temp, temp1;            
        String tempString;
        
       JPanel choosePanel [] = new JPanel [row+2];
       choosePanel[0] = new JPanel();
       choosePanel[0].add(new Label(title));
       choosePanel[choosePanel.length-1] = new JPanel();
       choosePanel[choosePanel.length-1].add(new Label("consider space field as zeros"));
       inputField  = new JTextField [matrix.length][matrix[0].length];
        
       
       //length loop
       for(temp = 1; temp <= matrix.length; temp++)
       {
			choosePanel[temp] = new JPanel();
           
			for(temp1 = 0; temp1 < matrix[0].length; temp1++)
			{
				inputField [temp-1][temp1] = new JTextField(3);
				choosePanel[temp].add(inputField [temp-1][temp1]);
               
				if(temp1 < matrix[0].length -1)
				{
					choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
				}
               
			}//end col loop
           
		}//end row loop
       
       result = JOptionPane.showConfirmDialog(null, choosePanel, 
               null, JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
     
      
        if(result == 0)
        {
          checkTextField(inputField);
			for(temp = 0; temp < matrix.length; temp++)
			{
				for(temp1 = 0; temp1 < matrix[0].length; temp1++)
				{
					tempString = inputField[temp][temp1].getText();
                
                
					if(isDouble(tempString))
					{
						matrix [temp][temp1] = Double.parseDouble(inputField[temp][temp1].getText());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You entered wrong elements");
                    
						//backup
						col = lastCol;
						row = lastRow;
                    
						return false;
					}                      
				}
			}
			return true;
		}
		else
		{	
          return false;
		}
      
    }//end get Inputs
    
    //for setting spaced fields as zeros
    private static void checkTextField (JTextField field [][] )
    {
        for(int temp = 0; temp < field.length; temp++)
        {
           for(int temp1 = 0; temp1 < field[0].length; temp1++)
           {
                if(field[temp][temp1].getText().equals(""))
                { 
					field[temp][temp1].setText("0");
				}
			}
		}
    }//end reset
    
    private void ChooseOperation()
    {
		frame = new JFrame("Matrix Calculator");
		frame.setSize(600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font myFont = new Font("Monospace", Font.PLAIN, 18);
		JPanel top = new JPanel();
		JPanel center = new JPanel();
		String[] groupCreators = {"Benitez, Ralph Andrei", "Buico, Cecilia Patrice", 
							    "Encelan, Martius Ken", "Torres, Chlarizza"}; 
		JLabel[] names = new JLabel[groupCreators.length];
		buttons();
		
		frame.setLayout(new BorderLayout());
		//
			JLabel heading = new JLabel("From the developers of 1CSA:", SwingConstants.CENTER);
			heading.setFont(myFont);
			heading.setForeground(Color.WHITE);
			
			top.setLayout(new GridLayout(groupCreators.length+4, 1));
			top.setBorder(new LineBorder(colorB, 15));
			top.setBackground(color);
			//
				top.add(Box.createHorizontalStrut(5)); // a spacer
				top.add(heading);
				top.add(new JLabel(""));
				for(int i = 0; i < groupCreators.length; i++)
				{
					names[i] = new JLabel(groupCreators[i], SwingConstants.CENTER);
					names[i].setFont(myFont);
					names[i].setForeground(Color.WHITE);
					names[i].setPreferredSize(new Dimension(50, 20));
					top.add(names[i]);
				}
				top.add(Box.createHorizontalStrut(5)); // a spacer
			//
			
			center.setLayout(new GridLayout(6, 1));
			center.setBorder(new LineBorder(colorB, 20));
			//
				for(int temp = 0; temp < choosePanel.length; temp++)
				{
					center.add(choosePanel[temp]);
				}
			//
			frame.add(top, BorderLayout.NORTH);
			frame.add(center, BorderLayout.CENTER);
		//
		frame.setResizable(false);
		frame.setVisible(true);
    }
	
	public void buttons()
	{
		for(int temp = 0; temp < choosePanel.length; temp++)
        {
            choosePanel[temp] = new JPanel ();
            choosePanel[temp].setBackground(color);
        }
		final int X = 200;
		final int Y = 50;
		final int SPACE_H = 5;
		choosePanel[0].add(Box.createHorizontalStrut(SPACE_H)); // a spacer
        
        newMatrix = new JButton("New Matrix");
        newMatrix.setPreferredSize(new Dimension(X,Y));
        newMatrix.addActionListener(this);
		newMatrix.setActionCommand("newMatrix");
        choosePanel[1].add(newMatrix);
		
		showMatrix = new JButton ("Show Matrix");
        showMatrix.setPreferredSize(new Dimension(X,Y));
        showMatrix.addActionListener(this);
        showMatrix.setActionCommand("showMatrix");
        choosePanel[1].add(showMatrix);
        
        plusB = new JButton ("Adding by Matrix");
        plusB.setPreferredSize(new Dimension(X,Y));
        plusB.addActionListener(this);
		plusB.setActionCommand("plusB");
        choosePanel[2].add(plusB);
        
        minusB = new JButton ("Subtracting by Matrix");
        minusB.setPreferredSize(new Dimension(X,Y));
        minusB.addActionListener(this);
		minusB.setActionCommand("minusB");
        choosePanel[2].add(minusB);
        
        multiplyB = new JButton ("Multiplying by matrix");
        multiplyB.setPreferredSize(new Dimension(X,Y));
        multiplyB.addActionListener(this);
		multiplyB.setActionCommand("showMatrix");
        choosePanel[3].add(multiplyB);
        
        nMultiplyB = new JButton ("Multiplying by scaler");
        nMultiplyB.setPreferredSize(new Dimension(X,Y));
        nMultiplyB.addActionListener(this);
		nMultiplyB.setActionCommand("nMultiplyB");
        choosePanel[3].add(nMultiplyB);
        
        nDivisionB = new JButton ("Dividing by scaler");
        nDivisionB.setPreferredSize(new Dimension(X,Y));
        nDivisionB.addActionListener(this);
		nDivisionB.setActionCommand("nDivisionB");
        choosePanel[4].add(nDivisionB);
        
        transposing = new JButton ("Transposing");
        transposing.setPreferredSize(new Dimension(X,Y));
        transposing.addActionListener(this);
		transposing.setActionCommand("transposing");
        choosePanel[4].add(transposing);
		
		choosePanel[5].add(Box.createHorizontalStrut(SPACE_H)); // a spacer
	}
   
    public void actionPerformed(ActionEvent e) 
    {
		switch(e.getActionCommand())
		{
			case "showMatrix":showMatrix( myMatrix, "Your Matrix");break;
			case "plusB":matrixPlusMatrix();break;
			case "minusB":matrixMinusMatrix();break;
			case "multiplyB":multiplyByMatrix();break;
			case "nMultiplyB":guiMultliplyByScaler();break;
			case "nDivisionB":divideByScaler();break;
			case "transposing":guiTransposing(myMatrix);break;
			case "newMatrix":newMatrix(); break;
		}
    }//end action performed
    
    private static void showMatrix(double [][] matrix, String title)
    {
        int temp, temp1;//temprature variable
        
       JPanel choosePanel [] = new JPanel [matrix.length+1];
       choosePanel[0] = new JPanel ();
       choosePanel[0].add( new JLabel (title) );
   
		for(temp = 1; temp <= matrix.length; temp++)
		{
			choosePanel[temp] = new JPanel();
			for(temp1 = 0; temp1 < matrix[0].length; temp1++)
			{
				if(matrix[temp-1][temp1] == -0)
				{
					matrix[temp-1][temp1] = 0; 
				}
				choosePanel[temp].add(new JLabel(String.format("%.2f", matrix[temp-1][temp1])));
               
				if(temp1 < matrix[0].length -1)
				{
					choosePanel[temp].add(Box.createHorizontalStrut(15)); // a spacer
				}
               
			}//end col loop
           
		}//end row loop
       
		if(col == 0 || row == 0)
		{
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		}
		else
		{
			JOptionPane.showMessageDialog(null, choosePanel, null, 
			JOptionPane.PLAIN_MESSAGE, null);
		}  
    }//end show Matrix

	private static void matrixPlusMatrix()
	{
		if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
        }
        else
        {
			double m2[][]=new double [row][col];
			double sum[][] = new double [row][col];

			if(setElements(m2, "Fill Additional Matrix"))
			{
				for(int i=0;i<row;i++)
				{
					for(int j=0;j<col;j++)
					{
						sum[i][j]=myMatrix[i][j]+m2[i][j];
					}
				}
				showMatrix(sum, "Summation Result");
			}
        }//end else checking
	}//end plus matrix
    
	private static void matrixMinusMatrix ()
	{
		if(myMatrix.length < 1)
		{
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		}
		else
		{
			double m2[][]=new double [row][col];
			double sub[][] = new double [row][col];
			double temp [][] = new double [row][col];

			if(setElements(m2, "Fill Subtracting Matrix"))
			{
				for(int i=0;i<row;i++)
				{
					for(int j=0;j<col;j++)
					{
						temp[i][j]=(-1*m2[i][j]);
						sub[i][j]=myMatrix[i][j]+temp[i][j];
					}
				}
				showMatrix(sub, "Subtracting Result");
			}
		}//end else of checking
	}

	private static void multiplyByMatrix ()
	{
		JTextField wField = new JTextField(5); //col field
		int col2 = 0;
		double m2 [][] , results[][];
		int sum;
		  
		if(myMatrix.length < 1)
		{
			JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
		}
		else
		{
			//design input line
			JPanel choosePanel [] = new JPanel [2];
			choosePanel [0] = new JPanel();
			choosePanel [1] = new JPanel();
	  
			choosePanel[0].add(new JLabel("Enter Dimensions") );
		
			choosePanel[1].add(new JLabel("Rows:"));
			choosePanel[1].add(new JLabel(""+col));
			choosePanel[1].add(Box.createHorizontalStrut(15)); // a spacer
			choosePanel[1].add(new JLabel("Cols:"));
			choosePanel[1].add(wField);
			
			result = JOptionPane.showConfirmDialog(null, choosePanel, 
			null,JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
			
			if(result == 0)
			{
				if(wField.getText().equals(""))
				{
					col2 = 0;
				}
				else
				{
					if(isInt(wField.getText()) )
					{
						col2 = Integer.parseInt(wField.getText());  
					}
				}		 
				m2 = new double [col][col2];
				results = new double [row][col2];
				if(setElements(m2, "Fill multiplying matrix"))
				{
			  
					for ( int i = 0 ; i < row ; i++ )
					{
						for ( int j = 0 ; j < col2 ; j++ )
						{   
							sum = 0;
							for ( int k = 0 ; k < col ; k++ )
							{
								sum +=  myMatrix[i][k]*m2[k][j];
							}
		 
					   results[i][j] = sum;
						}
					}
				
				showMatrix(results, "Mulitiplication Result");
			 
				}//elements checking
			}//dimensions checking
			else
			{	
			  return;
			}  
		}//end else of checking
	}//end multiply by matrix method

    private static void guiMultliplyByScaler ()
	{
		double[][]results=new double [row][col];
		double x;
		String tempString;
		
		if(myMatrix.length < 1)
			{
				JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
				return;
			}
		   
		tempString = JOptionPane.showInputDialog(null, 
				"Enter the scaler number for multiplying");

		if (tempString == null) //cancel option
		{
			return;
		}
		
		else if(!tempString.equals(""))
			x= Double.parseDouble(tempString);
		else
		{
			JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
			return;
		}
		results = multliplyByScaler(myMatrix, x);
		
		showMatrix(results, "Multiplication Result");
	}//end multiply by number

    private static double [][] multliplyByScaler(double [][] matrix , double x)
	{
		double[][]results=new double [row][col];
		int i,j;
		
		for (i=0;i<matrix.length;i++)
		{
			for(j=0;j<matrix[0].length;j++)
			{
			results[i][j] = x*matrix[i][j];
			}
		}
		
		return results;
	}//end multiply by number

    private static void divideByScaler ()
	{
		double[][]results=new double [row][col];
		int i,j;
		double x;
		String tempString;
		
		if(myMatrix.length < 1)
			{
				JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
				return;
			}
		
		//prompting for the scaler
		tempString = JOptionPane.showInputDialog("Enter the scaler number for dividing");
		
		
		if (tempString == null) //cancel option
		{
			return;
		}
		else if(!tempString.equals(""))
			x= Double.parseDouble(tempString);
		
		else
		{
			JOptionPane.showMessageDialog(null, "You haven't entered a scaler");
			return;
		}
		
		if(x == 0)
		{
			JOptionPane.showMessageDialog(null, "We can't divide by 0");
			return;
		}
		for (i=0;i<row;i++)
		{
			for(j=0;j<col;j++)
			{
				results[i][j] = myMatrix[i][j] / x;
			}
		}
		
		showMatrix(results, "Dividing Result");
	}//end dividing by number
    	
    private static void guiTransposing (double [][] matrix)
    {
        if(myMatrix.length < 1)
        {
            JOptionPane.showMessageDialog(null, "You haven't entered any matrix");
            return ;
        }
        
       
        double [][] transMatrix = new double[matrix[0].length][matrix.length];
        
        transMatrix = transposing(matrix);
        showMatrix(transMatrix, "Transposed Matrix");
    }
    
    private static double [][] transposing(double [][] matrix)
    {
        double [][] transportMatrix = new double[matrix[0].length][matrix.length];
        int temp1, temp; //termprature variable
        
        for(temp = 0 ; temp < matrix[0].length; temp++)
        {
            for(temp1 = 0; temp1 < matrix.length; temp1++)
            {
                 transportMatrix[temp][temp1]  = 
                         matrix[temp1][temp]; //swap (temp, temp1)
            }
        }
		
        return transportMatrix;
    }
    
    
	private static boolean isInt(String str)
	{
		int temp;
		if (str.length() == '0')
		{   
			return false;
		}
		
		for(temp = 0; temp < str.length();temp++)
		{
			if(str.charAt(temp) != '+' && str.charAt(temp) != '-'
			&& !Character.isDigit(str.charAt(temp)))
			{
				return false;
			}
		}
       return true;
	}
   
	private static boolean isDouble (String str)
	{
		int temp;
		if (str.length() == '0')
		{   
           return false;
		}
		
		for(temp = 0; temp < str.length();temp++)
		{
			if(str.charAt(temp) != '+' && str.charAt(temp) != '-' && str.charAt(temp) != '.'
			&& !Character.isDigit(str.charAt(temp)))
			{
				return false;
			}
		}
		return true;
	}
   
    private static void newMatrix ()
    {        
        getDimension();
    }
    public static void main (String [] args)
    {
        new MatrixCalculator();
    }
}//end class

