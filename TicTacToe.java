import java.io.*;
class TicTacToe
{   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main()throws IOException
    {
        int gcount=0,flag,b=0;String ch1="";
        if(gcount==0)
        {
            System.out.println("Welcome to Tic Tac Toe Game");
            gcount=1;
        }

        do
        {
            char a[][]={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
            System.out.println("\n\nPLay :\n1 Player\n  Or,\n2 PLayers");
            String f=br.readLine();
            if(f.charAt(0)=='1')
                ai(a);
            else
                normal(a);
            System.out.println("\n\nPlay Again?");
        }while(br.readLine().equalsIgnoreCase("yes"));
    }

    public static void normal(char a[][])throws IOException
    {
        int flag=0,b=0;
        System.out.println("\nPlayer 1 has : X\nPlayer 2 has : O\n");
        if(Math.random()>=0.5)
        {
            System.out.println("Player 2 Starts");
            flag=2;
        }
        else
        {
            System.out.println("Player 1 Starts");
            flag=1;
        }
        for(int i=1;i<=9;i++)
        {
            System.out.println("\nPlayer "+flag+" : Enter row and column number");
            int r=Integer.parseInt(br.readLine());
            int c=Integer.parseInt(br.readLine());
            if(r<1||r>3||c<1||c>3)
            {
                System.out.println("Invalid!!");
                --i;
                continue;
            }
            char ch;
            if(flag==2)
                ch='O';
            else
                ch='X';
            if(a[r-1][c-1]==' ')
                a[r-1][c-1]=ch;
            else
            {
                System.out.println("Position Occupied Already !!");
                --i;
                continue;
            }
            cpos(a);
            flag=flag==2?1:2;
            if(i>=5)
                b=check(a);
            if(b==1)
            {
                flag=flag==2?1:2;
                System.out.println("\nPlayer " + flag + " WINS !! ");
                break;
            }
        }
        if(b==0)
            System.out.println("\nA Draw Game");
    }

    public static void cpos(char a[][])
    {
        char line=' ';
        System.out.println("\nCurrent Position :");
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(j<2)
                    line = '|';
                else
                    line=' ';
                System.out.print(a[i][j]+" " + line +" ");
            }
            if(i<2)
                System.out.println("\n--|---|---");
        }
    }

    public static int check(char a[][])
    {
        char c[]=new char[3];int flag=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                c[j]=a[i][j];
            if(c[0]==c[1]&&c[1]==c[2]&&c[2]!= ' ')
            {
                flag=1;
                break;
            }
        }
        if(flag!=1)
        {
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)

                    c[j]=a[j][i];
                if(c[0]==c[1]&&c[1]==c[2]&&c[2]!= ' ')
                {
                    flag=1;
                    break;
                }
            }
        }
        if(flag!=1)
        {
            for(int j=0;j<3;j++)
            {
                c[j]=a[j][j];
            }
            if(c[0]==c[1]&&c[1]==c[2]&&c[2]!= ' ')
                flag=1;
        }
        if(flag!=1)
        {
            for(int j=0;j<3;j++)
            {
                c[j]=a[j][2-j];
            }
            if(c[0]==c[1]&&c[1]==c[2]&&c[2]!= ' ')
                flag=1;
        }
        return flag;
    }  

    public static void ai(char a[][])throws IOException
    {
        int flag=0,r=0,c=0,b=0;String ch1; 
        if(Math.random()>=0.5)
        {
            System.out.println("\nComputer Starts");
            flag=2;
        }
        else
        {
            System.out.println("\nPlayer Starts");
            flag=1;
        }
        for(int i=1;i<=9;i++)
        {
            if(flag==1)
            {
                System.out.println("\nPlayer : Enter row and column number");
                r=Integer.parseInt(br.readLine());
                c=Integer.parseInt(br.readLine());
                System.out.print("\f");
                if(r<1||r>3||c<1||c>3)
                {
                    System.out.println("Invalid!!");
                    --i;
                    continue;
                }
                char ch;
                if(flag==2)
                    ch='O';
                else
                    ch='X';
                if(a[r-1][c-1]==' ')
                    a[r-1][c-1]=ch;
                else
                {
                    System.out.println("Position Occupied Already !!");
                    --i;
                    continue;
                }
            }
            else
            { 
                int y=0;
                if(i>=4)
                {
                    y = cposibility(a,'O');
                    if(y==-1)
                        y = cposibility(a,'X');
                }
                if(i<4||y==-1)
                {
                    if(i==1)
                    {   
                        r=(int)(Math.random()*2.0);
                        c=r;
                    }
                    else
                    {
                        r=think(a,i);
                        c=r%10;
                        r/=10;
                    }
                    if(a[r][c]==' ')
                        a[r][c]='O';
                    else
                    {
                        --i;
                        continue;
                    }
                }
                if(y!=-1&&i>=4&&a[y/10][y%10]==' ')
                    a[y/10][y%10]='O';
                else if(y!=-1&&i>=4&&a[y/10][y%10]!=' ')
                {
                    --i;
                    continue;
                }
                System.out.println("\nComputer's Move"); 
            }           
            cpos(a);              
            flag=flag==2?1:2;
            if(i>=5)
                b=check(a);
            if(b==1&&flag==2)
            {
                System.out.println("\nPlayer WINS !! ");
                break;
            }
            else if(b==1&&flag==1)
            {
                System.out.println("\nComputer WINS !! ");
                break;
            }
        }
        if(b==0)
            System.out.println("\nA Draw Game");
    }

    public static int cposibility(char a[][],char t)
    {
        for(int i =0;i<3;i++)
        {
            if(a[i][0]==a[i][1]&&a[i][1]==t&&a[i][2]==' ')
                return (i*10)+2;
            if (a[0][i]==a[1][i]&&a[1][i]==t&&a[2][i]==' ')
                return 20+i;
            if (a[i][2]==a[i][1]&&a[i][1]==t&&a[i][0]==' ')
                return (i*10);
            if(a[2][i]==a[1][i]&&a[1][i]==t&&a[0][i]==' ')
                return i;
            if (a[i][2]==a[i][0]&&a[i][0]==t&&a[i][1]==' ')
                return (i*10)+1;
            if(a[2][i]==a[0][i]&&a[0][i]==t&&a[1][i]==' ')
                return 10+i;
        }
        if(a[0][0]==a[1][1]&&a[1][1]==t&&a[2][2]==' ')
            return 22;
        if(a[0][2]==a[1][1]&&a[1][1]==t&&a[2][0]==' ')
            return 20;
        if(a[2][2]==a[1][1]&&a[1][1]==t&&a[0][0]==' ')
            return 0;
        if(a[2][0]==a[1][1]&&a[1][1]==t&&a[0][2]==' ')
            return 2;
        if(a[2][2]==a[0][0]&&a[0][0]==t&&a[1][1]==' ')
            return 11;
        if(a[2][0]==a[0][2]&&a[0][2]==t&&a[1][1]==' ')
            return 11;    
        if(a[0][1]==a[1][0]&&a[0][1]==t&&a[0][2]==' '&&a[2][0]==' '&&a[0][0]==' ')
            return 0;
        if(a[0][1]==a[1][2]&&a[0][1]==t&&a[0][0]==' '&&a[2][2]==' '&&a[0][2]==' ')
            return 2;
        if(a[1][2]==a[2][1]&&a[2][1]==t&&a[2][0]==' '&&a[0][2]==' '&&a[2][2]==' ')
            return 22;
        if(a[1][0]==a[2][1]&&a[1][0]==t&&a[2][2]==' '&&a[0][0]==' '&&a[2][0]==' ')
            return 20;
        return -1;
    }

    public static int think(char a[][],int t)
    {
        int posr=0,posc=0;
        outer:for(int i = 0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(a[i][j]=='O')
                {
                    posr=i;
                    posc=j;
                    break outer;
                }
            }
        }
        if((posc==posr||posr==2-posc)&&posr!=1&&(a[1][1]==' '||t<=3))
        {
            if(a[0][2]==' ' && a[0][0]=='O' && a[0][1]==' '&&(a[1][1]==' '||a[1][2]==' '))
            return 2;           
            else if(a[2][0]==' '&& posr==0 && (a[1][0]==' '||a[0][1]==' '))
            return 20;
            else if(a[2][2]==' '&& posr==0 && (a[1][0]==' '||a[0][1]==' '))
            return 22;
            if(a[0][0]=='O'&&a[2][0]=='O'&&(a[1][1]==' '||a[1][0]==' '))
            {
                if(a[0][1]==' ')
                return 2;
                else if(a[2][1]==' ')
                return 22;
            }
        }
        for(int i = 0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(a[i][j]==' ')
                return i*10+j;
            }
        }
        return 0;
        }
    }