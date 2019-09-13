package AIprograms;
import static AIprograms.BestFirstSearch.sortByValue;
import java.util.*;
public class HillClimb {
    Map <Character, LinkedList<Character>> adjList;
    Map <Character, Integer> Heuristic_node;
    Map <Character, Integer> goal_nodes;
    Map <Character, Integer> start_nodes;
    Map <Character, Integer> candidate_list;
    HillClimb(){
        adjList = new HashMap<Character, LinkedList<Character>>();
        Heuristic_node = new HashMap<Character,Integer>();
        goal_nodes = new HashMap<Character,Integer>();
        start_nodes = new HashMap<Character,Integer>();
        candidate_list = new HashMap<Character,Integer>();}
    void addList(Character parent,LinkedList<Character> child){adjList.put(parent,child);}
    void add_Heuristic_node(Character key,Integer value){Heuristic_node.put(key,value);}
    void add_goal_nodes(Character key,Integer value){goal_nodes.put(key,value);}
    void add_start_nodes(Character key,Integer value){start_nodes.put(key,value);}
    LinkedList<Character> getChild(Character c){
        LinkedList<Character> temp = new LinkedList<Character>();
        for(int i=0;i<adjList.get(c).size();i++){
            if(adjList.get(c).get(0)!='-')
                temp.add(adjList.get(c).get(i));}
        return temp;}
    Integer getHeuristic(Character c){
        Integer temp=0;
        Set<Map.Entry<Character, Integer>> heuristicSet=Heuristic_node.entrySet();
        for(Map.Entry i:heuristicSet){
            if(i.getKey().equals(c)){
                temp=(Integer)i.getValue();
            }}return temp;}
    public static Map<Character, Integer> sortByValue(Map<Character, Integer> hm) {  // Create a list from elements of HashMap 
        List<Map.Entry<Character, Integer> > list = new LinkedList<Map.Entry<Character, Integer> >(hm.entrySet()); // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() { 
            public int compare(Map.Entry<Character, Integer> o1,Map.Entry<Character, Integer> o2) { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            }}); // put data from sorted list to hashmap  
        Map<Character, Integer> temp = new LinkedHashMap<Character, Integer>(); 
        for (Map.Entry<Character, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue());} 
        return temp;}
    void search(int choice){
        Set<Map.Entry<Character, Integer>> goalSet=goal_nodes.entrySet();
        Set<Map.Entry<Character, Integer>> startSet=start_nodes.entrySet();
        Map<Character, Integer> candidate_list1 = new HashMap<Character,Integer>();
        LinkedList<Character> childNodes = new LinkedList<Character>();
        int min=9999,max=0;
        int flag=0;
        for(Map.Entry j:startSet){
            for(Map.Entry i:goalSet){
                if((i.getKey().equals(j.getKey())) && (i.getValue().equals(j.getValue()))){
                    System.out.println("Goal Node Found!");
                    System.out.println(j.getKey()+" : "+j.getValue());
                    return;}}}
        candidate_list=start_nodes;
        if(choice==1){
                while(!candidate_list.isEmpty()){
                int size=candidate_list.size();
                Character tempKey =(Character)candidate_list.keySet().toArray()[size-1];
                Integer tempValue = (Integer)candidate_list.values().toArray()[size-1];
                System.out.print(tempKey+" = "+tempValue+" ");
                for(Map.Entry i:goalSet){
                        if((tempKey.equals(i.getKey())) && (tempValue.equals(i.getValue()))){
                            System.out.println();
                            System.out.println("Goal Node Found!");
                            flag=1;
                            return;}}
                if(max<=tempValue){
                    max=tempValue;
                    candidate_list.clear();
                    childNodes=getChild(tempKey);
                    for(int i=0;i<childNodes.size();i++){
                        Character tempKey1 = childNodes.get(i);
                        Integer tempValue1 = getHeuristic(tempKey1);
                        candidate_list.put(tempKey1,tempValue1);}
                    candidate_list1=sortByValue(candidate_list);
                    candidate_list=candidate_list1;}  
                else{
                    System.out.println();
                    System.out.print("Local Maxima Found!. ");
                    return;}}
            if(flag==0){
                System.out.println();
                System.out.print("Local Maxima Found!. ");
                return;}}
        else if(choice==2){
                while(!candidate_list.isEmpty()){
                Character tempKey =(Character)candidate_list.keySet().toArray()[0];
                Integer tempValue = (Integer)candidate_list.values().toArray()[0];
                System.out.print(tempKey+" = "+tempValue+" ");
                for(Map.Entry i:goalSet){
                        if((tempKey.equals(i.getKey())) && (tempValue.equals(i.getValue()))){
                            System.out.println();
                            System.out.println("Goal Node Found!");
                            flag=1;
                            return;}}
                if(min>=tempValue){
                    
                    min=tempValue;
                    candidate_list.clear();
                    childNodes=getChild(tempKey);
                    for(int i=0;i<childNodes.size();i++){
                        Character tempKey1 = childNodes.get(i);
                        Integer tempValue1 = getHeuristic(tempKey1);
                        candidate_list.put(tempKey1,tempValue1);}
                    candidate_list1=sortByValue(candidate_list);
                    candidate_list=candidate_list1;}
                else{
                    System.out.println();
                    System.out.print("Local Minima Found!. ");
                    return;}}
            if(flag==0){
                System.out.println();
                System.out.print("Local Minima Found!. ");
                return;}}
        else{return;}}
    public static void main(String[] args) {
        HillClimb hc = new HillClimb();
        Scanner s = new Scanner(System.in);
        LinkedList<Character> nodes = new LinkedList<Character>();
        LinkedList<Character> tempChildlist = new LinkedList<Character>();
        Character start_node,key,goal_key;
        Integer start_node_heuristic,value,goal_value,choice;
        int no_of_nodes,no_of_child_nodes,no_goal_nodes;
        System.out.print("Enter the Number of Nodes : ");
        no_of_nodes=s.nextInt();
        System.out.println("Enter the name Individual  nodes and their Heuristic values.");
        for(int i=0;i<no_of_nodes;i++){
            System.out.print("Enter the Node "+(i+1)+" name : ");
            key=s.next().charAt(0);
            System.out.print("Heuristic value? : ");
            value=s.nextInt();
            hc.add_Heuristic_node(key,value);
            nodes.add(key);}
        for(int i=0;i<no_of_nodes;i++){
            System.out.println("Enter the Child Nodes of "+nodes.get(i));
            System.out.print(nodes.get(i)+" Has How many Chidren? : ");
            no_of_child_nodes=s.nextInt();
            tempChildlist= new LinkedList<Character>();
                if(no_of_child_nodes!=0){
                    System.out.println("Child Nodes are : ");
                for(int j=0;j<no_of_child_nodes;j++){
                    tempChildlist.add(s.next().charAt(0));}
                hc.addList(nodes.get(i),tempChildlist);}
                else{
                    tempChildlist.add('-');
                     hc.addList(nodes.get(i),tempChildlist);}}
        System.out.println("Enter the name Individual  nodes and their Heuristic values.");
        System.out.print("Enter the no.of Goal nodes : ");
        no_goal_nodes=s.nextInt();
        for(int i=0;i<no_goal_nodes;i++){
            System.out.print("Enter the Goal Node "+(i+1)+" name : ");
            goal_key=s.next().charAt(0);
            System.out.print("Heuristic value? : ");
            goal_value=s.nextInt();
            hc.add_goal_nodes(goal_key,goal_value);}
        System.out.print("Enter the Start node : ");
        start_node=s.next().charAt(0);
        System.out.print("Heuristic value? : ");
        start_node_heuristic=s.nextInt();
        hc.add_start_nodes(start_node, start_node_heuristic);
        System.out.println();
        System.out.print("Start node ==> "+hc.start_nodes+"\n");
        System.out.print("Goal node ==> "+hc.goal_nodes+"\n");
        System.out.println("1.Maxima HillClimb.");
        System.out.println("2.Minima HillClimb.");
        System.out.print("Enter your Choice : ");
        choice=s.nextInt();
        if(choice==1){
            System.out.println("\nMaxima Hillclimb.");
            hc.search(choice);}
        else if(choice==2){
            System.out.println("\nMinima Hillclimb.");
            hc.search(choice);}
        else{System.out.println("Wronge Choice!!!");}}}