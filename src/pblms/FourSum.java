import java.util.*;
public class FourSum {
    public static void main(String[] args){
        FourSum f = new FourSum();
        List<List<Integer>> result =  
        // f.fourSum(new int[]{91277418,66271374,38763793,4092006,11415077,60468277,1122637,72398035,-62267800,22082642,60359529,-16540633,92671879,-64462734,-55855043,-40899846,88007957,-57387813,-49552230,-96789394,18318594,-3246760,-44346548,-21370279,42493875,25185969,83216261,-70078020,-53687927,-76072023,-65863359,-61708176,-29175835,85675811,-80575807,-92211746,44755622,-23368379,23619674,-749263,-40707953,-68966953,72694581,-52328726,-78618474,40958224,-2921736,-55902268,-74278762,63342010,29076029,58781716,56045007,-67966567,-79405127,-45778231,-47167435,1586413,-58822903,-51277270,87348634,-86955956,-47418266,74884315,-36952674,-29067969,-98812826,-44893101,-22516153,-34522513,34091871,-79583480,47562301,6154068,87601405,-48859327,-2183204,17736781,31189878,-23814871,-35880166,39204002,93248899,-42067196,-49473145,-75235452,-61923200,64824322,-88505198,20903451,-80926102,56089387,-58094433,37743524,-71480010,-14975982,19473982,47085913,-90793462,-33520678,70775566,-76347995,-16091435,94700640,17183454,85735982,90399615,-86251609,-68167910,-95327478,90586275,-99524469,16999817,27815883,-88279865,53092631,75125438,44270568,-23129316,-846252,-59608044,90938699,80923976,3534451,6218186,41256179,-9165388,-11897463,92423776,-38991231,-6082654,92275443,74040861,77457712,-80549965,-42515693,69918944,-95198414,15677446,-52451179,-50111167,-23732840,39520751,-90474508,-27860023,65164540,26582346,-20183515,99018741,-2826130,-28461563,-24759460,-83828963,-1739800,71207113,26434787,52931083,-33111208,38314304,-29429107,-5567826,-5149750,9582750,85289753,75490866,-93202942,-85974081,7365682,-42953023,21825824,68329208,-87994788,3460985,18744871,-49724457,-12982362,-47800372,39958829,-95981751,-71017359,-18397211,27941418,-34699076,74174334,96928957,44328607,49293516,-39034828,5945763,-47046163,10986423,63478877,30677010,-21202664,-86235407,3164123,8956697,-9003909,-18929014,-73824245}, 
         //    -236727523);
        //f.fourSum(new int[]{0,0,0,0}, 0);
        f.fourSum(new int[]{-3,-1,0,1,2}, 0);
        for(List<Integer> set : result){
            System.out.println(set);
        }
    }
    private static class Tuple{
        List<Integer> indices;
        long sum;
        public Tuple(List<Integer> indices, long sum){
            this.indices = indices;
            this.sum = sum;
        }
        //Deep Copy Constructor
        public Tuple(Tuple newTuple){
            this.indices = new ArrayList<Integer>();
            this.indices.addAll(newTuple.indices);
            this.sum = newTuple.sum;
        }
        public static boolean haveDifferentIndices(Tuple a, Tuple b){
            if(a != null && b != null){
                if(a.indices.size() > 0){
                    if(a.indices.size() == b.indices.size()){
                        HashSet<Integer> bIndices = new HashSet<Integer>();
                        for(int i=0; i<b.indices.size(); i++)
                            bIndices.add(b.indices.get(i));
                        for(int i=0; i<a.indices.size(); i++){
                            if(bIndices.contains(a.indices.get(i)))
                                return false;
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        @Override
        public String toString(){
            return "Sum = " + this.sum + "  Indices = " + this.indices;
        }
    }
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int k = 3;
        int n = num.length;
        if(n >= k){
            result = kSum(num, target, k);
        }
        return result;
    }
    private static List<List<Integer>> kSum(int[] num, int target, int k){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(k == 0)
            return result;
        if(k == 1){
            List<Integer> res = new ArrayList<Integer>();
            for(int i=0; i<num.length; i++){
                if(num[i] == target)
                    res.add(num[i]);
            }
            result.add(res);
            return result;
        }
        
        //get all pairs of size k/2 (tuple is a list of indices)
        List<Tuple> tuples = getAllTuples(num, k/2);
        //put them in treemap with key = sum of a tuple and value = list of tuples which have same sum
        Map<Long, List<Tuple>> tuplesMap = new TreeMap<Long, List<Tuple>>();
        for(Tuple t : tuples){                    
            if(tuplesMap.get(t.sum) != null){                    
                tuplesMap.get(t.sum).add(t);
            }else{
                List<Tuple> newTuples = new ArrayList<Tuple>();
                newTuples.add(t);
                tuplesMap.put(t.sum, newTuples);                    
            }
        }            
        if(k % 2 == 0){
            /*for each entry in hashmap
                if -entry exists in map
                    get its value and for each value
                    if indices do not conflict with indices of entry then 
                        add value to result*/
            for(Map.Entry<Long,List<Tuple>> entry : tuplesMap.entrySet()){                
                if(entry.getKey() > target/2){
                    break;
                }
                long remainingSum = target - entry.getKey();                
                if(tuplesMap.get(remainingSum) != null){
                    List<Tuple> matchingTuples = tuplesMap.get(remainingSum);
                    for(Tuple matchingTuple : matchingTuples){                        
                        for(Tuple entryTuple : entry.getValue()){                                     
                            if(Tuple.haveDifferentIndices(entryTuple, matchingTuple)){                               
                                List<Integer> kTupleIndices = new ArrayList<Integer>();
                                kTupleIndices.addAll(entryTuple.indices);
                                kTupleIndices.addAll(matchingTuple.indices);
                                Collections.sort(kTupleIndices);
                                if(!result.contains(kTupleIndices))                               
                                    result.add(kTupleIndices);
                            }
                        }
                    }
                }
            }
        }else{
            //For odd k we have extra loop
            for(int i=0; i<num.length; i++){
                int newTarget = target - num[i];
                for(Map.Entry<Long,List<Tuple>> entry : tuplesMap.entrySet()){                
                    if(entry.getKey() > newTarget/2){
                        break;
                    }
                    long remainingSum = newTarget - entry.getKey();                
                    if(tuplesMap.get(remainingSum) != null){
                        List<Tuple> matchingTuples = tuplesMap.get(remainingSum);
                        for(Tuple matchingTuple : matchingTuples){                        
                            for(Tuple entryTuple : entry.getValue()){                                     
                                if(Tuple.haveDifferentIndices(entryTuple, matchingTuple)){                               
                                    List<Integer> kTupleIndices = new ArrayList<Integer>();
                                    kTupleIndices.addAll(entryTuple.indices);
                                    kTupleIndices.addAll(matchingTuple.indices);
                                    if(!kTupleIndices.contains(i)){ //Check for extra loop index also
                                        kTupleIndices.add(i);
                                        Collections.sort(kTupleIndices);
                                        if(!result.contains(kTupleIndices))                               
                                            result.add(kTupleIndices);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    //Basic idea is 
    //for i=start to end in input
        //concat(input[i], all tuples of size k-1 from next element to end of input)
    private static List<Tuple> getAllTuples(int[] num, int size){
        List<Tuple> tuples = new ArrayList<Tuple>();
        Tuple currentTuple = new Tuple(new ArrayList<Integer>(), 0);
        getAllTuplesHelper(num, size, 0, currentTuple, tuples);
        return tuples;
    }
    private static void getAllTuplesHelper(int[] num, int size, int startIndex, Tuple currentTuple, List<Tuple> tuples){
        if(size == 0){            
            Tuple copy = new Tuple(currentTuple);            
            tuples.add(copy);              
        }else{
            for(int i=startIndex; i<num.length; i++){
                //Add current element to the result so far
                currentTuple.indices.add(i);
                currentTuple.sum += num[i];
                //Generate all tuples of size k-1 starting from i+1
                getAllTuplesHelper(num, size-1, i+1, currentTuple, tuples);
                //Remove the current element from result
                currentTuple.indices.remove(currentTuple.indices.size()-1);
                currentTuple.sum -= num[i];
            }
        }
    }
}