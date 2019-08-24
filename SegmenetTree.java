public class SegmenetTree
{
   public static void buildSG(int arr[],int sgTree[],int start,int end,int treeNode)
   {
      if(start==end)
      {
          sgTree[treeNode]=arr[start];
          return;
      }
      
      int mid=(Start+end)/2;
      buildSG(arr,sgTree,start,mid,2*treeNode);
      buildSG(arr,sgTree,mid+1,end,2*treeNode+1);
      
      sgTree[treeNode]=sgTree[2*treeNode]+sgTree[2*treeNode+1];
   }
   
   
   
   public static void main(String args[])
   {
      int arr[]=new int[]{1,2,3,4,5,6,7,8,9,10};
     /*
       int N=arr.length;
       int sgTree=new int [2*N];
       if you are writing code in C++ Above will be fine 
       
       other wise in Java -----
       if the size of given array is power of 2 then segment tree size will be (size of array)*2-1 
                                                 otherwise (next power of 2 near to that number)*2-1.
      */
      int height = (int)(Math.log(n)/Math.log(2)) + 1;
      int tree_nodes = (int) Math.pow(2, height + 1);
      
      int sgTree=new int [tree_nodes];
      
      buildSG(arr,sgTree,0,N-1,1);
      
      //printing
      for(int i=1;i<tree_nodes;i++)
      {
         System.out.print(sgTree[i]+" ");
      }
   }
   
}
