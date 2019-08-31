/*

     It is used for finding maximum sum of any subarray in a given range in the array.
     for example:
     if we have an array like:
                  1, 4, -1, 3, 42, 7, 5, 9, -2

         then if the query is (0,4)  the maximum sum of any sub array inside it will be 12
                                   same for  0, 3-----> 7
                                             2, 4----->7
                                             4, 8------>27



code by AnuragSingh-01
*/



import java.util.Scanner;

public class MaxSubarraySumInGivenRange {

	
    static class Node
    {
        int maxsum;
        int sum;
        int BPS;  //best left prefix sum..
        int BSS; //best right suffix sum...
    }
    
    public static void buildTree(int arr[],Node tree[],int start,int end,int treeNode)
    {
        if(start == end)
        {
            tree[treeNode].maxsum=arr[start];
            tree[treeNode].sum=arr[start];
            tree[treeNode].BPS=arr[start];
            tree[treeNode].BSS=arr[start];
            return;
        }
        
        int mid=(start+end)/2;
        buildTree(arr,tree,start,mid,2*treeNode);
        buildTree(arr,tree,mid+1,end,2*treeNode+1);
        
        Node L=tree[2*treeNode];
        Node R=tree[2*treeNode+1];
  
        tree[treeNode].maxsum = Math.max(R.maxsum,Math.max(L.maxsum,Math.max((L.BSS+R.BPS),Math.max((L.sum+R.BPS),(L.BSS+R.sum)))));
        tree[treeNode].sum = L.sum+R.sum;
        tree[treeNode].BPS = Math.max(L.BPS,(L.sum+R.BPS));
        tree[treeNode].BSS = Math.max(R.BSS,(L.BSS+R.sum));
        
                                         
    }
               
                                         
   public static void updateTree(int arr[],Node tree[],int start,int end,int treeNode,int idx,int value)
   {
       
       if(start==end)
       {
            arr[idx]=value;
            tree[treeNode].maxsum=value;
            tree[treeNode].sum=value;
            tree[treeNode].BPS=value;
            tree[treeNode].BSS=value;
            return;
       }
        
       int mid=(start+end)/2;
       if(idx>mid)
           updateTree(arr,tree,mid+1,end,2*treeNode+1,idx,value);
       else
           updateTree(arr,tree,start,mid,2*treeNode,idx,value);
       
        Node L=tree[2*treeNode];
        Node R=tree[2*treeNode+1];
  
        tree[treeNode].maxsum = Math.max(R.maxsum,Math.max(L.maxsum,Math.max((L.BSS+R.BPS),Math.max((L.sum+R.BPS),(L.BSS+R.sum)))));
        tree[treeNode].sum = L.sum+R.sum;
        tree[treeNode].BPS = Math.max(L.BPS,(L.sum+R.BPS));
        tree[treeNode].BSS = Math.max(R.BSS,(L.BSS+R.sum));
        
  }
                                         
   public static Node query(Node tree[],int start,int end, int treeNode,int left,int right)
   {
       if(end<left || right<start)
       {
            Node temp=new Node();
                 temp.maxsum=-123456789;   
                 temp.sum=-123456789;  //any highly negative value but not use Integer.MIN_VALUE because it can
                 temp.BPS=-123456789;  //create problem....like overflow can make it positive..
                 temp.BSS=-123456789;  //like when we add -2 to -2147483648 it becomes 2147483646.
                                       
                                        //****Integer.MAX_VALUE + 1 == Integer.MIN_VALUE*****
           return temp;
       }
       
       if(left<=start && end<=right)
       {
           return tree[treeNode];
       }
       
       int mid=(start+end)/2;
       
       Node L=query(tree,start,mid,2*treeNode,left,right);
       Node R=query(tree,mid+1,end,2*treeNode+1,left,right);
       
        Node temp=new Node();
        temp.maxsum = Math.max(R.maxsum,Math.max(L.maxsum,Math.max((L.BSS+R.BPS),Math.max((L.sum+R.BPS),(L.BSS+R.sum)))));
        temp.sum = L.sum+R.sum;
        temp.BPS = Math.max(L.BPS,(L.sum+R.BPS));
        temp.BSS = Math.max(R.BSS,(L.BSS+R.sum));
        
        return temp; 
   }

	public static void main(String[] args)
    {
		// Write your code here
        Scanner in=new Scanner(System.in);
        

        int t=in.nextInt();
        for(int k=0;k<t;k++)
        {
            int N=in.nextInt();
            int q=in.nextInt();
            int arr[]=new int[N];
            for(int i=0;i<N;i++)
                arr[i]=in.nextInt();
            
            Node tree[]=new Node[4*N];
            
            for(int i=0;i<(4*N);i++)
            {
               tree[i]=new Node();    
            }
            
            buildTree(arr,tree,0,N-1,1);
            
            for(int i=0;i<q;i++)
            {
                int type=in.nextInt();    //type 1 fror query and type 2 for updation....
                int x=in.nextInt();          
                int y=in.nextInt();          
                
                   if(type==1)    //for type 1 x is left and y is right.
                     System.out.println(query(tree,0,N-1,1,x-1,y-1).maxsum); //x-1 && y-1 isliye karna padh kyuki 0 based indexing nahi
                   else                   				   //If there is 1 based indexing then we have to do -1 from x and y
		   {                                                    
                     updateTree(arr,tree,0,N-1,1,x-1,y);   //type 2 for updation...at x by y..
                   } 					   //since it is type 2 then x is the index the index that's why we have subtract 1
	    }     					   //from x and y will be the value and thats why we don't need to sub 1 from it... 
        }    
	}

}
