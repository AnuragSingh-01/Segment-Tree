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
        long maxsum;
        long sum;
        long BPS;  //best left prefix sum..
        long BSS; //best right suffix sum...
    }
    
    public static void buildTree(long arr[],Node tree[],int start,int end,int treeNode)
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
               
                                         
   public static void updateTree(long arr[],Node tree[],int start,int end,int treeNode,int idx,int value)
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
                 temp.maxsum=Long.MIN_VALUE;
                 temp.sum=Long.MIN_VALUE;
                 temp.BPS=Long.MIN_VALUE;
                 temp.BSS=Long.MIN_VALUE;
           
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
        int N=in.nextInt();
        
        long arr[]=new long[N];
        
        for(int i=0;i<N;i++)
            arr[i]=in.nextLong();
        
        Node tree[]=new Node[4*N];
        
        for(int i=0;i<(4*N);i++)
        {
           tree[i]=new Node();    
        }
        
        buildTree(arr,tree,0,N-1,1);
        
        // for(int i=0;i<(3*N);i++)
        // {
        //     System.out.print(tree[i].maxsum+" ");    
        // }
        
        int q=in.nextInt();
        
        for(int i=0;i<q;i++)
        {
            int x=in.nextInt();
            int y=in.nextInt();
            
            System.out.println(query(tree,0,N-1,1,x-1,y-1).maxsum);
        }
	}

}
