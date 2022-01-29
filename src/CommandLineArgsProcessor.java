import java.util.HashMap;
import java.util.Map;

public class CommandLineArgsProcessor {

    public static Map<String, Integer> processArgs(String[] args, Map<String, Integer> defaults) {

        Map<String, Integer> processedArgs = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("--")
                    && defaults.containsKey(arg.substring(2))
                    && (i + 1) < args.length
                    && args[i + 1].matches("^[-]?\\d+$")) {

                processedArgs.put(arg.substring(2), (int)Math.min(Long.parseLong(args[i + 1]), Integer.MAX_VALUE));
                i += 1;
            }
        }
        defaults.forEach(processedArgs::putIfAbsent);
        return processedArgs;
    }
}
