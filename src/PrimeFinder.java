import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrimeFinder {

    public static List<Integer> findPrimes(int lowerBound, int upperBound) throws InterruptedException, ExecutionException {

        List<Integer> primes = new ArrayList<>();

        if (lowerBound > upperBound)
            throw new IllegalArgumentException("The lower bound must be lesser than the upper bound");


        if (lowerBound < 3) {
            if (upperBound >= 2)
                primes.add(2);
            lowerBound = 3;

            if (upperBound < lowerBound)
                return primes;
        }

        int numThreads = Math.max(1, (int)Math.round((double)(upperBound - lowerBound + 1) / 12_500_000));

        ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);

        int partitionSize = (upperBound - lowerBound + 1) / numThreads;

        List<Callable<List<Integer>>> callables = new ArrayList<>();

        for (int i = lowerBound; i <= upperBound; i += partitionSize + 1) {
            int startingPosition = i;
            callables.add(() -> PrimeFinder.getPrimes(startingPosition, Math.min(upperBound, startingPosition + partitionSize)));
        }

        List<Future<List<Integer>>> futures = threadPool.invokeAll(callables);

        for (Future<List<Integer>> future : futures) {
            primes.addAll(future.get());
        }

        threadPool.shutdownNow();

        return primes;
    }

    private static List<Integer> getPrimes(int lowerBound, int upperBound) {

        List<Integer> primes = new ArrayList<>();

        outerloop:
        for (int possiblePrime = lowerBound; possiblePrime <= upperBound; possiblePrime++) {

            if ((possiblePrime % 2) == 0)
                continue;

            for (int possibleFactor = 3; (possibleFactor * possibleFactor) <= possiblePrime; possibleFactor += 2) {
                if ((possiblePrime % possibleFactor) == 0)
                    continue outerloop;
            }
            primes.add(possiblePrime);
        }

        return primes;
    }
}
