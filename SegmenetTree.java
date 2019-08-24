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
      int N=arr.length;
      int sgTree=new int [2*N];
      
      buildSG(arr,sgTree,0,N-1,1);
      
      //printing
      for(int i=1;i<2*N;i++)
      {
         System.out.print(sgTree[i]+" ");
      }
   }
   
}
