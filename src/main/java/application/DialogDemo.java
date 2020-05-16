package application;

import java.util.HashMap;
import java.util.Map;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;


public class DialogDemo {

public static void main(String[] args) throws Exception {

    System.out.println("Loading models...");

    Configuration configuration = new Configuration();

    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");


    LiveSpeechRecognizer recognizer =
            new LiveSpeechRecognizer(configuration);


    while (true) {
        System.out.println("a");
        recognizer.startRecognition(true);
        String result = recognizer.getResult().getHypothesis();
        if (result.startsWith("stop")) {
            System.out.println("Stopping");
            recognizer.stopRecognition();
            return;
        } else if (result.length() != 0) {
            System.out.println(result);
            System.out.println("Printed");
            recognizer.stopRecognition();
        } else {
            System.out.println("Nothing happening");
            recognizer.stopRecognition();
        }


    }
}
}