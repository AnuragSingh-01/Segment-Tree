/*
  Code by Anurag Singh
  AnuragSingh-01
*/

public class SegmenetTree
{
   //for building a Segment tree...It should be called first before any operation
   public static void buildSG(int arr[],int sgTree[],int start,int end,int treeNode)
   {
      if(start==end)
      {
          sgTree[treeNode]=arr[start];
          return;
      }
      
      int mid=(start+end)/2;
      buildSG(arr,sgTree,start,mid,2*treeNode);
      buildSG(arr,sgTree,mid+1,end,2*treeNode+1);
      
      sgTree[treeNode]=sgTree[2*treeNode]+sgTree[2*treeNode+1];
   }
   
   
   //this is for update operation.
    public static void updateTree(int arr[],int tree[],int start,int  end,int treeNode,int idx,int value)
    {
          if(start==end)
          {
              arr[idx]=value;
              tree[treeNode]=value;
             return;
          }
       
          int mid=(start+end)/2;
          if(idx>mid)
             updateTree(arr,tree,mid+1,end,2*treeNode+1,idx,value);
          else
             updateTree(arr,tree,start,mid,2*treeNode,idx,value); 
       
          tree[treeNode]=tree[2*treeNode]+tree[2*treeNode+1];
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
      
      int N=arr.length;
      int height = (int)(Math.log(N)/Math.log(2)) + 1;
      int tree_nodes = (int) Math.pow(2, height + 1);
      
      int sgTree[]=new int [tree_nodes];// We can also take  the size of Segment tree as 4*N if we dont wanat to do above two step, it willalso workfine.
                                        //int sgTree[]=new int[4*N] will alo be fine...
      buildSG(arr,sgTree,0,N-1,1);
      
      //printing after building segment tree...
      for(int i=1;i<tree_nodes;i++)
      {
         System.out.print(sgTree[i]+" ");
      }
      
      
      /*
        now updating..
        we will pass (arr, tree, (start index of array),(end indexof array),(starting index of segment tree which is 1),(index at which we need to update in array),(value to be updated t that index));
       */
      updateTree(arr,sgTree,0,N-1,1,2,10);
      
      System.out.println();
      for(int i=1;i<tree_nodes;i++)
      {
         System.out.print(sgTree[i]+" ");
      }
      
   }
   
}
