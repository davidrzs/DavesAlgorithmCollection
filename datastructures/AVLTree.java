
public class AVLTree {

	
	/**
	 * A TreeElement is a an element in our tree below. It is just a container to hold relevant data and doesn't offer any methods.
	 */
	static class TreeElement{
		int value;
		int count;
		int balance;
		int height = 1;
		int nr_of_kids;
		
		TreeElement leftChild = null;
		TreeElement rightChild = null;
		TreeElement parent = null;
		
		TreeElement(TreeElement parent, int value){
			this.parent = parent;
			this.value = value;
			count = 1;
		}
		
		//default constructor
		TreeElement(){
		}
	}
	
	/**
	 * AVL is a wrapper class which allows us to abstract the complexity away.
	 */
	public static class AVL{
		TreeElement root;
		int nr_of_elements;
		int nThirdValue = 0;
		TreeElement toBalance;
		int max = 0;
		
		//constructor
		AVL(){
			root = null;
		}
		
		
	   private void backPropagateSizeUpdate(TreeElement elem) {
			
		   TreeElement copy = elem;
		   while(elem.parent != null) {
				elem = elem.parent;
				elem.nr_of_kids++;
			}
	   }

	   
	   private void findNLargestIndex(int index) {
			if(index == nr_of_elements + 1) {
				return; // cause the following code wouldn't make any sense if we try to acces an index that didn't exist -> we start counting at 1.
			}
			//System.out.println("index: " + index + " nr_of_elements: " + nr_of_elements);
			TreeElement ce = root;
			//TreeElement te = root;			
			//System.out.println("value: "+ te.value+ " count: " + te.count + " nr_of_kids:" +te.nr_of_kids);

			while(true) {
				int leftSize = 0;
				if(null != ce.leftChild) {
					leftSize = ce.leftChild.count + ce.leftChild.nr_of_kids;
					//System.out.println("current leftsize: " + leftSize);
				}
				//check if its in current node:
				if(index > leftSize && index <= leftSize + ce.count) {
					nThirdValue = ce.value;
					break;
				} else if(index <= leftSize) {
					ce = ce.leftChild;
				} else {
					ce = ce.rightChild;
					index = index - leftSize - ce.parent.count;
				}				
			}
		}
	   
	   
		
		   
	  void add(int newInt) {
		  addToTree(newInt);
		  if(toBalance != null)
			  balance(toBalance);
		/* some cleanup stuff...*/
			
		// since we have added an element:
		nr_of_elements += 1;
		// casting automatically floors number
		int idx = nr_of_elements / 3;
		// we get the floor(n/3)'rd largest element in this loop since it takes O(log(n))
		// and we are allowed to use that time during insertion but retrieval should be within O(1)
		findNLargestIndex(nr_of_elements + 1 - idx);
		//System.out.println("special elem is: " + nThirdValue);
	  }
	   
	   void addToTree(int newInt) {			
			//check if this is the first element we are inserting:
			if(root == null) {
				root = new TreeElement(null, newInt);
				root.count = 0; // will be incremented in while loop
			}
			
			//set max for O(1) retrieval:
			if(max < newInt) {
				max = newInt;
			}
			
			//recursivePrint(root);
			// 
			TreeElement currentElementWeAreLookingAt = root;
			// we loop until we break, every loop iteration we go one step further down.
			while(true) {
				//check if the element we currently have is the same as the one we are trying to insert.
				if(currentElementWeAreLookingAt.value == newInt) {
					//increase count of current value
					//System.out.println("duplicate at: " + newInt);
					currentElementWeAreLookingAt.count++;
					//now we need to backpropagate the size update:
					backPropagateSizeUpdate(currentElementWeAreLookingAt);
					//we have found our destination - break loop
					break;
				}
				TreeElement father = currentElementWeAreLookingAt;
				//now we must find out if we should go to the left or to the right.
				if(father.value < newInt) {
					// we go right
					currentElementWeAreLookingAt = father.rightChild;
					if(currentElementWeAreLookingAt == null) {
						father.rightChild = new TreeElement(father, newInt);
						backPropagateSizeUpdate(father.rightChild);
						toBalance = father;
						break;
					}
				} else {
					// father.value > newInt must be true
					currentElementWeAreLookingAt = father.leftChild;
					if(currentElementWeAreLookingAt == null) {
						father.leftChild = new TreeElement(father, newInt);
						backPropagateSizeUpdate(father.leftChild);
						toBalance = father;
						break;
					}
				}
			}

		}
	
	   
		
		// this method ensures our tree doesn't grow to heavily on one side.
		void balance(TreeElement el) {
			//calculate the balance of the current element to see what needs to be done
			updateBalance(el);
			
			//now we can find out what kind of case we are dealing with
			//if the following doesn't make any sense
			if(el.balance < -1) {
				 if (height(el.leftChild.leftChild) >= height(el.leftChild.rightChild)) {
						//System.out.println("rightrot around " + el.value);
					 el = rightRotation(el);
				 } else {
						//System.out.println("rightleftrot around " + el.value);
						el.leftChild = leftRotation(el.leftChild);
						el = rightRotation(el);
				 }
			} else if(el.balance > 1) {
				 if (height(el.rightChild.rightChild) >= height(el.rightChild.leftChild)) {
						//System.out.println("leftrot around " + el.value);
		               el = leftRotation(el);
				 } else {
						//System.out.println("rightleft around " + el.value);
						el.rightChild = rightRotation(el.rightChild);
						el = leftRotation(el);
				 }
			}
			//now we have "legal balances" on the level of el -> check if balances are ok on upper level
			//check if el is root:
			if (null == el.parent) {
				// we need to set el = new root if there is no parent since rotation has made this element root.
				root = el;
			} else {
				balance(el.parent);
			}
		}

	    
	    private TreeElement leftRotation(TreeElement el) {
	    	/* it is hard to comment this function -> if you draw it on a piece of paper it all makes sense.*/
	    	// linkes kind wird neuer Aufhängpunkt
	    	TreeElement pseudoRoot = el.rightChild;

	    	//nach oben erreichbar machen
	    	pseudoRoot.parent = el.parent;
	 
	        //von oben erreichbar machen
	        if (pseudoRoot.parent != null) {
	            if (pseudoRoot.parent.leftChild == el) {
	            	pseudoRoot.parent.leftChild = pseudoRoot;
	            } else {
	            	pseudoRoot.parent.rightChild = pseudoRoot;
	            }
	        }
	    	// linkes kind von el ist neu das rechte kind von pseudoroot
	    	el.rightChild = pseudoRoot.leftChild;
	    	// jetzt müssen wir dem umgehängten (falls es denn existiert) einen neuen "Vater zuteilen"
	        if (el.rightChild != null) {
	        	el.rightChild.parent = el;
	        }
	        // zum schluss hängen wir el links an pseudoroot
	        pseudoRoot.leftChild = el;
	        el.parent = pseudoRoot;
	 
	        // jetzt haben sie die Balances geändert da wir eine Rotation gemacht haben. --> wir updaten die und rufen dann in der obigen methode eventuell wieder einer Rotation auf
	        updateBalance(el);
	        updateBalance(pseudoRoot);
	 
	        return pseudoRoot;
	    }
		
		
		
	    private TreeElement rightRotation(TreeElement el) {
	    	/* it is hard to comment this function -> if you draw it on a piece of paper it all makes sense.*/
	    	// linkes kind wird neuer Aufhängpunkt
	    	TreeElement pseudoRoot = el.leftChild;

	    	//nach oben erreichbar machen
	    	pseudoRoot.parent = el.parent;
	 
	        //von oben erreichbar machen
	        if (pseudoRoot.parent != null) {
	            if (pseudoRoot.parent.rightChild == el) {
	            	pseudoRoot.parent.rightChild = pseudoRoot;
	            } else {
	            	pseudoRoot.parent.leftChild = pseudoRoot;
	            }
	        }
	    	// linkes kind von el ist neu das rechte kind von pseudoroot
	    	el.leftChild = pseudoRoot.rightChild;
	    	// jetzt müssen wir dem umgehängten (falls es denn existiert) einen neuen "Vater zuteilen"
	        if (el.leftChild != null) {
	        	el.leftChild.parent = el;
	        }
	        // zum schluss hängen wir el links an pseudoroot
	        pseudoRoot.rightChild = el;
	        el.parent = pseudoRoot;
	 
	        // jetzt haben sie die Balances geändert da wir eine Rotation gemacht haben. --> wir updaten die und rufen dann in der obigen methode eventuell wieder einer Rotation auf
	        updateBalance(el);
	        updateBalance(pseudoRoot);
	 
	        return pseudoRoot;
	    }
				
		/* methods needed to determine if balancing is necessary */
		void updateHeightAndKids(TreeElement el) {
			//check if it is null
			if(null != el) {
				el.nr_of_kids =  number_of_kids_and_count(el.leftChild) + number_of_kids_and_count(el.rightChild);
				el.height = 1 + max(height(el.leftChild), height(el.rightChild));
			}
		}
		
		int number_of_kids_and_count(TreeElement el) {
			 if (el == null)
		            return 0;
		        return el.nr_of_kids + el.count;
		}
		
		//formula from lecture notes
		void updateBalance(TreeElement el) {
			updateHeightAndKids(el);
			el.balance = height(el.rightChild) - height(el.leftChild);
			return;
		}
		
		/* start Helper functions  */
		int height(TreeElement el) {
			return null == el ? 0 : el.height;
		}
		
		int max(int a, int b) {
			return a > b ? a : b;
		}
		
		void print() {
			TreeElement te = root;
			recursivePrint(te, 0);
		}
		
		int size() {
			return nr_of_elements;
		}
		
		
		//for debugging
		void recursivePrint(TreeElement te, int level) {
			int parent = Integer.MIN_VALUE;
			if(te.parent != null) {
				parent = te.parent.value;
			}
			System.out.println("value: "+ te.value+ " count: " + te.count + " level: " + level + " nr_of_kids:" +te.nr_of_kids + " height: " + te.height + " parent: " + parent);
			if(te.leftChild != null) {
				recursivePrint(te.leftChild, level +1);
			}
			if(te.rightChild != null) {
				recursivePrint(te.rightChild,level +1);
			}
		}
		
		/* end Helper functions  */

		
	   }

	
}

