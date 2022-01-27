package AoC.day_14;

import AoC.data_manager.DataConverter;

import java.math.BigInteger;
import java.util.*;

public class Polymerization {

    private final Map<String,String> polymerizationMap;
    private final BigInteger INCREMENT = new BigInteger("1");

    public Polymerization(Map<String, String> polymerizationMap) {
        this.polymerizationMap = polymerizationMap;
    }

    public BigInteger calcBigPolymerResult(String polymerTemplate, int numOfSteps) {
        Map<PolymerTuple, BigInteger> polymerMap = createBigPolymer(polymerTemplate, numOfSteps);
        Map<String, BigInteger> frequencyMap = createBigFrequencyMap(polymerMap, polymerTemplate);
        return getNumberMostFrequentElement(frequencyMap).subtract(getNumberLeastFrequent(frequencyMap));
    }

    public Map<PolymerTuple, BigInteger> createBigPolymer(String polymerTemplate, int numOfSteps) {
        Map<PolymerTuple, BigInteger> polymerMap = createPolymerMap(polymerTemplate);

        for (int step = 1; step <= numOfSteps; step++) {
//            System.out.println(String.format("Processing step %s ...", step));
            polymerMap = growPolymer(polymerMap);
        }
        return polymerMap;
    }

    private Map<PolymerTuple, BigInteger> growPolymer(Map<PolymerTuple, BigInteger> polymerMap) {
        Iterator<PolymerTuple> tupleIterator = polymerMap.keySet().iterator();

        Map<PolymerTuple, BigInteger> newPolymerMap = new HashMap();
        while (tupleIterator.hasNext()) {
            PolymerTuple keyTuple = tupleIterator.next();
            String element = polymerizationMap.getOrDefault(keyTuple.toString(), null);
            BigInteger frequency = polymerMap.get(keyTuple);
            if (element != null) {
                addElementToMap(keyTuple.getElement1(), element, newPolymerMap, frequency);
                addElementToMap(element, keyTuple.getElement2(), newPolymerMap, frequency);
            }
        }
        return newPolymerMap;
    }

    private void addElementToMap(String element1, String element2, Map<PolymerTuple, BigInteger> map, BigInteger frequency) {
        PolymerTuple elementTuple = new PolymerTuple(element1, element2);
        if (!map.containsKey(elementTuple))
            map.put(elementTuple, frequency);
        else
            map.put(elementTuple, map.get(elementTuple).add(frequency));
    }

    private void addElementToMap(String element1, String element2, Map<PolymerTuple, BigInteger> map) {
        addElementToMap(element1, element2, map, INCREMENT);
    }

    private Map<PolymerTuple, BigInteger> createPolymerMap(String polymer) {
        LinkedList<String> polymerAsList = DataConverter.convertStringToStringList(polymer);
        Map<PolymerTuple, BigInteger> polymerMap = new HashMap();
        int indexP1 = 0;
        int indexP2 = 1;
        while (indexP2 < polymerAsList.size()) {
            addElementToMap(polymerAsList.get(indexP1), polymerAsList.get(indexP2), polymerMap);
            indexP1++;
            indexP2++;

        }
        return polymerMap;
    }

    public LinkedList<String> createPolymer(String polymerTemplate, int numOfSteps) {
        LinkedList<String> polymer = DataConverter.convertStringToStringList(polymerTemplate);

        for (int step = 1; step <= numOfSteps; step++) {
            LinkedList<String> tmpPolymer = new LinkedList();
            int indexP1 = 0;
            int indexP2 = 1;
            while (indexP2 < polymer.size()) {
                String pair = polymer.get(indexP1) + polymer.get(indexP2);
                String element = polymerizationMap.getOrDefault(pair, null);
                if (element != null) {
                    if (tmpPolymer.isEmpty())
                        tmpPolymer.add(polymer.get(indexP1));
                    tmpPolymer.add(element);
                    tmpPolymer.add(polymer.get(indexP2));
                }
                indexP1++;
                indexP2++;
            }
            polymer = new LinkedList(DataConverter.deepCopyList(tmpPolymer));
        }
        return polymer;
    }

    private BigInteger getNumberMostFrequentElement(Map<String, BigInteger> frequencyMap) {
        return Collections.max(frequencyMap.values());
    }

    private BigInteger getNumberLeastFrequent(Map<String, BigInteger> frequencyMap) {
        return Collections.min(frequencyMap.values());
    }

     Map<String, BigInteger> createBigFrequencyMap(Map<PolymerTuple, BigInteger> polymerMap, String polymerTemplate) {
        String lastElement = polymerTemplate.substring(polymerTemplate.length() - 1);

        Map<String, BigInteger> frequencyMap = new HashMap();

        Iterator<PolymerTuple> keyIterator = polymerMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            PolymerTuple polymerTuple = keyIterator.next();
            String element = polymerTuple.getElement1();
            BigInteger frequency = polymerMap.get(polymerTuple);
            if (!frequencyMap.containsKey(element))
                frequencyMap.put(element, new BigInteger("0"));
            frequencyMap.put(element, frequencyMap.get(element).add(frequency));
        }
        frequencyMap.put(lastElement, frequencyMap.get(lastElement).add(INCREMENT));
        return frequencyMap;
    }

}
