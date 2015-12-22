package net.mmhan;

import java.util.Random;

public class JokeRepo {
    public static String get(){
        String[] jokes = query();
        return jokes[new Random().nextInt(jokes.length)];
    }
    public static String[] query(){
        return new String[]{
            "Chuck Norris once gave a box of his old watches to a group of kids. these kids are now known as the power rangers.",
            "What happens to a frog's car when it breaks down?\n" +
                    "It gets toad away.",
            "Q: What is the difference between snowmen and snowwomen? \n" +
                    "A: Snowballs.",
            "I never wanted to believe that my Dad was stealing from his job as a road worker. But when I got home, all the signs were there."
        };
    }
}
