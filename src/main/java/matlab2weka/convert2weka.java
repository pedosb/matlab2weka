package matlab2weka;

import java.util.Arrays;
import java.util.HashSet;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class convert2weka {

	private Instances wekaInstances; 
	/**
	 * convert2weka.java
	 * Purpose: Converts the input data, which are in forms of double and/or string matrices into an WEKA Inatances object. 
	 * All copyrights reserved.
	 *
	 * @author Sunghoon Ivan Lee
	 * @version 1.0 2/25/2015
	 */
	
	/**
	 * Constructor that creates a WEKA-Instances object when the inputs
	 * contain only nominal attributes, and when the class
	 * attribute is a numerical variable (i.e. for regression). 
	 * The resulting object will be stored in the global variable 
	 * "wekaInstances". 
	 *
	 * @param  name A string variable that represents the name of this dataset (e.g., "training data").
	 * @param  attrNameString A string vector that contains the names of the attributes of the nominal features.
	 * @param  dataString A 2D numeric matrix that contains the values of the nominal data. Rows represents the attributes. Columns represents the data points.
	 * @param  classLbel A numeric vector that contains the values of the class data. 
	 * @param  hasClass A boolean variable indicating whether or not to include the "class" attribute (e.g., true for classification/regreesion, and false for clustering)
	 * @return  Nothing
	 */
	public convert2weka(String name, String attrNameNumeric[], double dataNumeric[][], double classLabel[], boolean hasClass)
	{		
		int numAttrNumeric = attrNameNumeric.length;
		int numAttr = numAttrNumeric; 
		if (hasClass)
			numAttr++; // with the class label
		int numDataNumeric = dataNumeric[0].length;
		int numData = numDataNumeric;
						
		FastVector vec = new FastVector(numAttrNumeric + 1);
		// adding data attributes
		for (int i = 0; i < numAttrNumeric; i++)
			vec.addElement(new Attribute(attrNameNumeric[i]));

		// adding the class label	
		if (hasClass)	
			vec.addElement(new Attribute("class"));
		
		wekaInstances = new Instances(name, vec, numData);		
		// adding data values		
		for (int i = 0; i < numData; i++)
		{
			Instance inst = new Instance(numAttr);
			// adding numeric value
			for (int j = 0; j < numAttrNumeric; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(j, dataNumeric[j][i]);
			}		
			if (hasClass)	{
				// adding class value
				inst.setDataset(wekaInstances);
				inst.setValue(numAttr - 1, classLabel[i]);
			}
			wekaInstances.add(inst);
		}		
		if (hasClass)
			wekaInstances.setClassIndex(numAttr - 1);		
	}
	
	/**
	 * Constructor that creates a WEKA-Instances object when the inputs
	 * contain only numerical attributes, and when the class
	 * attribute is a nominal variable (i.e. for classification). 
	 * The resulting object will be stored in the global variable 
	 * "wekaInstances". 
	 *
	 * @param  name A string variable that represents the name of this dataset (e.g., "training data")
	 * @param  attrNameNumeric A string vector that contains the names of the attributes of the numerical features.
	 * @param  dataNumeric A 2D numeric matrix that contains the values of the numeric data. Rows represents the attributes. Columns represents the data points.
	 * @param  classLbel A string vector that contains the values of the class data. 
	 * @param  hasClass A boolean variable indicating whether or not to include the "class" attribute (e.g., true for classification/regreesion, and false for clustering)
	 * @return  Nothing
	 */
	public convert2weka(String name, String attrNameNumeric[], double dataNumeric[][], String classLabel[], boolean hasClass)
	{		
		int numAttrNumeric = attrNameNumeric.length;
		int numAttr = numAttrNumeric; 
		if (hasClass)
			numAttr++; // with the class label
		int numDataNumeric = dataNumeric[0].length;
		int numData = numDataNumeric;
		
		FastVector vec = new FastVector(numAttrNumeric + 1);
		// adding data attributes
		for (int i = 0; i < numAttrNumeric ; i++)
			vec.addElement(new Attribute(attrNameNumeric[i]));

		// adding the class label
		if (hasClass)	{
			// getting the unique strings within the classLabel.
			String[] uClasslabel = new HashSet<String>(Arrays.asList(classLabel)).toArray(new String[0]);
			FastVector classValues = new FastVector(uClasslabel.length);
			
			for (int j = 0; j < uClasslabel.length; j++)
				classValues.addElement(uClasslabel[j]);
			vec.addElement(new Attribute("class", classValues));
		}
		
		wekaInstances = new Instances(name, vec, numData);		
		// adding data values		
		for (int i = 0; i < numData; i++)
		{
			Instance inst = new Instance(numAttr);
			// adding numeric value
			for (int j = 0; j < numAttrNumeric; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(j, dataNumeric[j][i]);
			}		
			
			// adding class value
			if (hasClass)	{
				inst.setDataset(wekaInstances);
				inst.setValue(numAttr - 1, classLabel[i]);
			}
			wekaInstances.add(inst);
		}	
		if (hasClass)
			wekaInstances.setClassIndex(numAttr - 1);		
	}
	
	/**
	 * Constructor that creates a WEKA-Instances object when the inputs
	 * contain only nominal attributes, and when the class
	 * attribute is a numeric variable (i.e. for regression). 
	 * The resulting object will be stored in the global variable 
	 * "wekaInstances".
	 *
	 * @param  name A string variable that represents the name of this dataset (e.g., "training data")
	 * @param  attrNameString A string vector that contains the names of the attributes of the nominal features.
	 * @param  dataString A 2D numeric matrix that contains the values of the nominal data. Rows represents the attributes. Columns represents the data points.
	 * @param  classLbel A numeric vector that contains the values of the class data. 
	 * @param  hasClass A boolean variable indicating whether or not to include the "class" attribute (e.g., true for classification/regreesion, and false for clustering)
	 * @return  Nothing
	 */	
	public convert2weka(String name, String attrNameString[], String dataString[][], double classLabel[], boolean hasClass)
	{		
		int numAttrString = attrNameString.length;
		int numAttr = numAttrString; 
		if (hasClass)
			numAttr++; // with the class label
		int numDataString = dataString[0].length;
		int numData = numDataString;
		
		FastVector vec = new FastVector(numAttrString + 1);

		/// adding string attribute
		for (int i = 0; i < numAttrString; i++) {
			String[] uAttrVals = new HashSet<String>(Arrays.asList(dataString[i])).toArray(new String[0]);
			FastVector attrValVector = new FastVector(uAttrVals.length);
			for (int j = 0; j < uAttrVals.length; j++)
				attrValVector.addElement(uAttrVals[j]);
			vec.addElement(new Attribute(attrNameString[i], attrValVector));
		}
		/// adding the class label
		if (hasClass)
			vec.addElement(new Attribute("class"));
		
		wekaInstances = new Instances(name, vec, numData);		
		// adding data values		
		for (int i = 0; i < numData; i++)
		{
			Instance inst = new Instance(numAttr);

			// adding string value
			for (int j = 0; j < numAttrString; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(j, dataString[j][i]);
			}
			// adding class value
			if (hasClass) {
				inst.setDataset(wekaInstances);
				inst.setValue(numAttr - 1, classLabel[i]);
			}
			wekaInstances.add(inst);
		}
		if (hasClass)
			wekaInstances.setClassIndex(numAttr - 1);		
	}
	
	/**
	 * Constructor that creates a WEKA-Instances object when the inputs
	 * contain only nominal attributes, and when the class
	 * attribute is a nominal variable (i.e. for classification). 
	 * The resulting object will be stored in the global variable 
	 * "wekaInstances". 
	 *
	 * @param  name A string variable that represents the name of this dataset (e.g., "training data")
	 * @param  attrNameString A string vector that contains the names of the attributes of the nominal features.
	 * @param  dataString A 2D numeric matrix that contains the values of the nominal data. Rows represents the attributes. Columns represents the data points.
	 * @param  classLbel A string vector that contains the values of the class data. 
	 * @param  hasClass A boolean variable indicating whether or not to include the "class" attribute (e.g., true for classification/regreesion, and false for clustering)
	 * @return  Nothing
	 */	
	public convert2weka(String name, String attrNameString[], String dataString[][], String classLabel[], boolean hasClass)
	{		
		int numAttrString = attrNameString.length;
		int numAttr = numAttrString; 
		if (hasClass)
			numAttr++; // with the class label
		int numDataString = dataString[0].length;
		int numData = numDataString;
		
		FastVector vec = new FastVector(numAttrString + 1);

		/// adding string attribute
		for (int i = 0; i < numAttrString; i++) {
			String[] uAttrVals = new HashSet<String>(Arrays.asList(dataString[i])).toArray(new String[0]);
			FastVector attrValVector = new FastVector(uAttrVals.length);
			for (int j = 0; j < uAttrVals.length; j++)
				attrValVector.addElement(uAttrVals[j]);
			vec.addElement(new Attribute(attrNameString[i], attrValVector));
		}
		/// adding the class label
		if (hasClass) {
			// getting the unique strings within the classLabel.
			String[] uClasslabel = new HashSet<String>(Arrays.asList(classLabel)).toArray(new String[0]);
			FastVector classValues = new FastVector(uClasslabel.length);
			for (int j = 0; j < uClasslabel.length; j++)
				classValues.addElement(uClasslabel[j]);
			vec.addElement(new Attribute("class", classValues));
		}
		
		wekaInstances = new Instances(name, vec, numData);		
		// adding data values		
		for (int i = 0; i < numData; i++)
		{
			Instance inst = new Instance(numAttr);

			// adding string value
			for (int j = 0; j < numAttrString; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(j, dataString[j][i]);
			}
			// adding class value
			if (hasClass){
				inst.setDataset(wekaInstances);
				inst.setValue(numAttr - 1, classLabel[i]);
			}
			wekaInstances.add(inst);
		}			
		if (hasClass)
			wekaInstances.setClassIndex(numAttr - 1);		
	}
	
	/**
	 * Constructor that creates a WEKA-Instances object when the inputs
	 * contain both numerical and nominal attributes, and when the class
	 * attribute is a nominal variable (i.e. for classification). 
	 * The resulting object will be stored in the global variable 
	 * "wekaInstances". 
	 *
	 * @param  name A string variable that represents the name of this dataset (e.g., "training data")
	 * @param  attrNameNumeric A string vector that contains the names of the attributes of the numerical features.
	 * @param  dataNumeric A 2D numeric matrix that contains the values of the numeric data. Rows represents the attributes. Columns represents the data points.
	 * @param  attrNameString A string vector that contains the names of the attributes of the nominal features.
	 * @param  dataString A 2D numeric matrix that contains the values of the nominal data. Rows represents the attributes. Columns represents the data points.
	 * @param  classLbel A string vector that contains the values of the class data. 
	 * @param  hasClass A boolean variable indicating whether or not to include the "class" attribute (e.g., true for classification/regreesion, and false for clustering)
	 * @return  Nothing
	 */
	public convert2weka(String name, String attrNameNumeric[], double dataNumeric[][], String attrNameString[], String dataString[][], String classLabel[], boolean hasClass)
	{		
		int numAttrNumeric = attrNameNumeric.length;
		int numAttrString = attrNameString.length;
		int numAttr = numAttrNumeric + numAttrString;
		if (hasClass)
			numAttr++; // with the class label
		int numDataNumeric = dataNumeric[0].length;
		int numDataString = dataString[0].length;
		int numData = numDataNumeric + numDataString;
		
		FastVector vec = new FastVector(numAttrNumeric + numAttrString + 1);
		/// adding data attributes
		for (int i = 0; i < numAttrNumeric; i++)
			vec.addElement(new Attribute(attrNameNumeric[i]));
		/// adding string attribute
		for (int i = 0; i < numAttrString; i++) {
			String[] uAttrVals = new HashSet<String>(Arrays.asList(dataString[i])).toArray(new String[0]);
			FastVector attrValVector = new FastVector(uAttrVals.length);
			for (int j = 0; j < uAttrVals.length; j++)
				attrValVector.addElement(uAttrVals[j]);
			vec.addElement(new Attribute(attrNameString[i], attrValVector));
		}
		/// adding the class label
		if (hasClass) {
			// getting the unique strings within the classLabel.
			String[] uClasslabel = new HashSet<String>(Arrays.asList(classLabel)).toArray(new String[0]);
			FastVector classValues = new FastVector(uClasslabel.length);
			for (int j = 0; j < uClasslabel.length; j++)
				classValues.addElement(uClasslabel[j]);
			vec.addElement(new Attribute("class", classValues));
		}
		
		wekaInstances = new Instances(name, vec, numData);		
		// adding data values		
		for (int i = 0; i < numData; i++)
		{
			Instance inst = new Instance(numAttr);
			// adding numeric value
			for (int j = 0; j < numAttrNumeric; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(j, dataNumeric[j][i]);
			}
			// adding string value
			for (int j = 0; j < numAttrString; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(numAttrNumeric + j, dataString[j][i]);
			}
			if (hasClass) {
				// adding class value
				inst.setDataset(wekaInstances);
				inst.setValue(numAttr - 1, classLabel[i]);
			}
			wekaInstances.add(inst);
		}		
		if (hasClass)
			wekaInstances.setClassIndex(numAttr - 1);		
	}
	
	/**
	 * Constructor that creates a WEKA-Instances object when the inputs
	 * contain both numerical and nominal attributes, and when the class
	 * attribute is a numerical variable (i.e. for regression). 
	 * The resulting object will be stored in the global variable 
	 * "wekaInstances". 
	 *
	 * @param  name A string variable that represents the name of this dataset (e.g., "training data")
	 * @param  attrNameNumeric A string vector that contains the names of the attributes of the numerical features.
	 * @param  dataNumeric A 2D numeric matrix that contains the values of the numeric data. Rows represents the attributes. Columns represents the data points.
	 * @param  attrNameString A string vector that contains the names of the attributes of the nominal features.
	 * @param  dataString A 2D numeric matrix that contains the values of the nominal data. Rows represents the attributes. Columns represents the data points.
	 * @param  classLbel A numeric vector that contains the values of the class data. 
	 * @param  hasClass A boolean variable indicating whether or not to include the "class" attribute (e.g., true for classification/regreesion, and false for clustering)
	 * @return  Nothing
	 */
	public convert2weka(String name, String attrNameNumeric[], double dataNumeric[][], String attrNameString[], String dataString[][], double classLabel[], boolean hasClass)
	{		
		int numAttrNumeric = attrNameNumeric.length;
		int numAttrString = attrNameString.length;
		int numAttr = numAttrNumeric + numAttrString;
		if (hasClass)
			numAttr++; // with the class label
		int numDataNumeric = dataNumeric[0].length;
		int numDataString = dataString[0].length;
		int numData = numDataNumeric + numDataString;
		
		FastVector vec = new FastVector(numAttrNumeric + numAttrString + 1);
		/// adding data attributes
		for (int i = 0; i < numAttrNumeric; i++)
			vec.addElement(new Attribute(attrNameNumeric[i]));
		/// adding string attribute
		for (int i = 0; i < numAttrString; i++) {
			String[] uAttrVals = new HashSet<String>(Arrays.asList(dataString[i])).toArray(new String[0]);
			FastVector attrValVector = new FastVector(uAttrVals.length);
			for (int j = 0; j < uAttrVals.length; j++)
				attrValVector.addElement(uAttrVals[j]);
			vec.addElement(new Attribute(attrNameString[i], attrValVector));
		}
		/// adding the class label	
		if (hasClass)
			vec.addElement(new Attribute("class"));
		
		wekaInstances = new Instances(name, vec, numData);		
		// adding data values		
		for (int i = 0; i < numData; i++)
		{
			Instance inst = new Instance(numAttr);
			// adding numeric value
			for (int j = 0; j < numAttrNumeric; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(j, dataNumeric[j][i]);
			}
			// adding string value
			for (int j = 0; j < numAttrString; j++)
			{
				inst.setDataset(wekaInstances);
				inst.setValue(numAttrNumeric + j, dataString[j][i]);
			}
			// adding class value
			if (hasClass){
				inst.setDataset(wekaInstances);
				inst.setValue(numAttr - 1, classLabel[i]);
			}
			wekaInstances.add(inst);
		}	
		if (hasClass)
			wekaInstances.setClassIndex(numAttr - 1);		
	}
	
	/**
	 * Returns the WEKA-Instances object created by the constructor.  
	 *
	 * @return  the WEKA-Instances object
	 */
	public Instances getInstances()
	{
		return wekaInstances;
	}
}