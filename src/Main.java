import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        Instant start = Instant.now();

        Map<String, Integer> processArgs = CommandLineArgsProcessor.processArgs(args, Map.of("lower", 0, "upper", 100_000_000));

        List<Integer> primes = PrimeFinder.findPrimes(processArgs.get("lower"), processArgs.get("upper"));

        Duration executionDuration = Duration.between(start, Instant.now());

        long primeSum = primes.stream().mapToLong(Integer::longValue).reduce(0, Long::sum);
        List<Integer> maxPrimes = primes.subList((primes.size() >= 10) ? primes.size() - 11 : 0, primes.size());

        String resultDetails = executionDuration.toMillis() + " millis  " + primes.size() + "  " + primeSum;

        List<String> lines = List.of(resultDetails, maxPrimes.toString());

        Files.write(Paths.get("primes.txt"), lines);
    }
}
