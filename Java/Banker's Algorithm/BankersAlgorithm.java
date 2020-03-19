/**	BankersAlgorithm:
	
	Each input consists of 4 things:
	Types of Resource
	Number of individual Resources
	Number of Process
	Max resources and Allocated Resources

	Sample Input:
	4
	3 14 12 12
	5
	0 0 1 2 0 0 1 2
	1 7 5 0 1 0 0 0
	2 3 5 6 1 3 5 4
	0 6 5 2 0 6 3 2
	0 6 5 6 0 0 1 4

	Sample Output:
	Process                 Max                                             Allocated
	P1      |       0       0       1       2       |       0       0       1       2       |
	P2      |       1       7       5       0       |       1       0       0       0       |
	P3      |       2       3       5       6       |       1       3       5       4       |
	P4      |       0       6       5       2       |       0       6       3       2       |
	P5      |       0       6       5       6       |       0       0       1       4       |
	Safe Sequence: 1 3 4 5 2
	*/

import java.io.File;
import java.util.*;

class BankersAlgorithm{
	int type,n;
	int[] avail;
	int[][] P;

	void input()throws Exception{
		String fl = new File(BankersAlgorithm.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath()+"\\test.txt";
		File file = new File(fl);
    Scanner sc = new Scanner(file);
		type = Integer.parseInt(sc.nextLine());
		int[] res = new int[type];

		String tmp = sc.nextLine();
		StringTokenizer st1 = new StringTokenizer(tmp, " ");
		for(int i=0;i<res.length;i++)
		res[i] = Integer.parseInt(st1.nextToken());

		n = Integer.parseInt(sc.nextLine());

		P = new int[n][2*type];
		avail = new int[type];
		int[] ta = new int[type];

		for(int i=0;i<n;i++){
			tmp = sc.nextLine();
			st1 = new StringTokenizer(tmp, " ");
			for(int j=0;j<2*type;j++){
				P[i][j] = Integer.parseInt(st1.nextToken());
				if(j>=type)
				ta[j-type] += P[i][j];
			}
		}
		for(int i=0;i<type;i++)
		avail[i] = res[i] - ta[i];
	}

	void display(){
		System.out.print("Process");

		for(int i=0;i<=type/2;i++){
			System.out.print("\t");
		}
		System.out.print("Max");
		for(int i=0;i<=type/2;i++){
			System.out.print("\t");
		}

		for(int i=0;i<=type/2;i++){
			System.out.print("\t");
		}
		System.out.print("Allocated");
		for(int i=0;i<=type/2;i++){
			System.out.print("\t");
		}
		System.out.println();

		for(int i=0;i<n;i++){
		System.out.print("P"+(i+1)+"\t|");
			for(int j=0;j<type;j++){
				System.out.print("\t"+P[i][j]);
			}
			System.out.print("\t|");
			for(int j=type;j<2*type;j++){
				System.out.print("\t"+P[i][j]);
			}
			System.out.println("\t|");
		}
	}

	String bank(){
		String seq="";
		int count,cz;
		boolean flag;
		do{
			count=0;
			for(int i=0;i<n;i++){
				flag = false;
				cz = 0;
				for(int j=0;j<type;j++){
					if(P[i][j]-P[i][type+j]>avail[j]){
						flag = true;
						break;
				}
				else if (P[i][type+j]==0)
					cz += 1;
					if(cz==type)
						flag = true;
			}
					if(flag){
						count++;
						continue;
				}
					count--;
					seq = seq+(i+1)+" ";
					for(int j=0;j<type;j++){
						avail[j] +=  P[i][type+j];
						P[i][type+j] = 0;
					}
				}
		}while(count!=n);
		if(seq.length()/2==n)
			return seq;
		else
			return null;
	}

	public static void main(String[] args)throws Exception{
		BankersAlgorithm obj = new BankersAlgorithm();
		obj.input();
		obj.display();
		String seq  = obj.bank();
		if(seq!=null)
		System.out.println("Safe Sequence: " + seq);
		else
		System.out.println("Unsafe Sequence");
	}
}
