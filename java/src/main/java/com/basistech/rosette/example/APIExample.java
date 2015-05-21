package com.basistech.rosette.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.basistech.rosette.api.RosetteAPI;
import com.basistech.rosette.api.RosetteAPIException;
import com.basistech.rosette.api.RosetteAPIParameterException;
import com.basistech.rosette.model.CategorizationModel;
import com.basistech.rosette.model.Category;
import com.basistech.rosette.model.CategoryOptions;
import com.basistech.rosette.model.CategoryResponse;
import com.basistech.rosette.model.ConstantsResponse;
import com.basistech.rosette.model.Decompounding;
import com.basistech.rosette.model.EntityResponse;
import com.basistech.rosette.model.ExtractedEntity;
import com.basistech.rosette.model.HanReadings;
import com.basistech.rosette.model.InfoResponse;
import com.basistech.rosette.model.InputUnit;
import com.basistech.rosette.model.LanguageCode;
import com.basistech.rosette.model.LanguageDetectionResult;
import com.basistech.rosette.model.LanguageInfoResponse;
import com.basistech.rosette.model.LanguageResponse;
import com.basistech.rosette.model.Lemma;
import com.basistech.rosette.model.LinkedEntity;
import com.basistech.rosette.model.LinkedEntityResponse;
import com.basistech.rosette.model.MorphologyResponse;
import com.basistech.rosette.model.Name;
import com.basistech.rosette.model.NameMatcherRequest;
import com.basistech.rosette.model.NameMatcherResponse;
import com.basistech.rosette.model.NameMatcherResult;
import com.basistech.rosette.model.NameTranslationRequest;
import com.basistech.rosette.model.NameTranslationResponse;
import com.basistech.rosette.model.PartOfSpeech;
import com.basistech.rosette.model.PingResponse;
import com.basistech.rosette.model.SentenceResponse;
import com.basistech.rosette.model.Sentiment;
import com.basistech.rosette.model.SentimentModel;
import com.basistech.rosette.model.SentimentOptions;
import com.basistech.rosette.model.SentimentResponse;
import com.basistech.rosette.model.TokenResponse;
import com.basistech.rosette.model.TranslatedNameResult;

/**
 * Provides examples on how to use the {@link com.basistech.rosette.api RosetteAPI} endpoints.
 */
public class APIExample {

    private static RosetteAPI rosetteAPI;

    /**
     * Main program.
     * Creates a RosetteAPI instance with the API key defined in rosette.api.key property.
     * Calls all RosetteAPI methods as a demonstration of usage.
     *
     * @param args not used
     * @throws URISyntaxException
     * @throws IOException
     * @throws RosetteAPIParameterException
     */
    public static void main(String[] args) throws URISyntaxException, IOException, RosetteAPIParameterException {
        String website = "http://www.basistech.com";
        URL url = new URL(website);
        String text = "I live in Boston, Massachusetts. I work for Basis Technology.";
        String apiKey = System.getProperty("rosette.api.key");

        if (apiKey == null) {
            usage();
            return;
        }

        rosetteAPI = new RosetteAPI(apiKey);
        ClassLoader cl = APIExample.class.getClassLoader();

        doGetInfo();
        doPing();

        doLanguageInfo();
        doInfo(RosetteAPI.EndpointInfo.ENTITIES);
        doInfo(RosetteAPI.EndpointInfo.LINKED_ENTITIES);
        doInfo(RosetteAPI.EndpointInfo.CATEGORIES);
        doInfo(RosetteAPI.EndpointInfo.SENTIMENT);
        doInfo(RosetteAPI.EndpointInfo.TRANSLATED_NAME);
        doInfo(RosetteAPI.EndpointInfo.MATCHED_NAME);

        doNameMatcher("John Doe", "John Doe");
        doNameMatcher("John Doe", "Jon Doe");
        doNameMatcher("习近平", "Xi Jinping");

        doNameTranslation("John Doe", LanguageCode.KOREAN);
        doNameTranslation("习近平", LanguageCode.ENGLISH);

        doLanguage(url);
        doLanguage(text);
        doLanguage(cl.getResourceAsStream("Chinese.txt"));

        doMorphology(url);
        doMorphology(text);
        doMorphology(cl.getResourceAsStream("Chinese.txt"));

        doEntity(url);
        doEntity(text);
        doEntity(cl.getResourceAsStream("English.txt"));

        doLinkedEntity(url);
        doLinkedEntity(text);
        doLinkedEntity(cl.getResourceAsStream("English.txt"));

        doCategories(url);
        doCategories(url, new CategoryOptions(CategorizationModel.QAG));
        doCategories(url, new CategoryOptions(CategorizationModel.QAG, true));
        doCategories(url, new CategoryOptions(CategorizationModel.QAG, true, 10));
        doCategories(text);
        doCategories(cl.getResourceAsStream("English.txt"));

        doSentiment(url);
        doSentiment(url, new SentimentOptions(SentimentModel.REVIEW));
        doSentiment(url, new SentimentOptions(SentimentModel.REVIEW, true));
        doSentiment(text);
        doSentiment(text, new SentimentOptions(SentimentModel.SHORT_STRING, true));
        doSentiment(cl.getResourceAsStream("English.txt"));
        doSentiment(cl.getResourceAsStream("English.txt"), LanguageCode.ENGLISH, new SentimentOptions(SentimentModel.REVIEW, true));
        doSentiment(cl.getResourceAsStream("Chinese.txt"));
        rosetteAPI.setDebugOutput(true);
        doSentiment(cl.getResourceAsStream("Chinese.txt"));
        rosetteAPI.setDebugOutput(false);

        doTokens(url);
        doTokens(text);
        doTokens(cl.getResourceAsStream("English.txt"));
        doTokens(cl.getResourceAsStream("Chinese.txt"), LanguageCode.CHINESE);

        doSentences(url);
        doSentences(text);
        doSentences(text, LanguageCode.ENGLISH, InputUnit.sentence);
        doSentences(cl.getResourceAsStream("English.txt"));
        doSentences(cl.getResourceAsStream("Chinese.txt"));
    }

    private static void doInfo(RosetteAPI.EndpointInfo endpoint) {
        try {
            ConstantsResponse response = rosetteAPI.getInfo(endpoint);
            printInfo(endpoint.getDescription(), response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private static void printInfo(String description, ConstantsResponse response) {
        System.out.printf("%s\tversion: %s\tbuild: %s\tsupport: %s\n", description, response.version, response.build, response.support);
        System.out.println();
    }

    private static void doLanguageInfo() {
        try {
            LanguageInfoResponse response = rosetteAPI.getLanguageInfo();
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private static void print(LanguageInfoResponse response) {
        System.out.println("LanguageInfo");
        Map<String, Set<String>> supportedLanguages = response.getSupportedLanguages();
        for (Map.Entry<String, Set<String>> stringSetEntry : supportedLanguages.entrySet()) {
            System.out.print("language: " + stringSetEntry.getKey());
            System.out.println(" scripts: " + stringSetEntry.getValue().toString());
        }
        Map<String, Set<String>> supportedScripts = response.getSupportedScripts();
        for (Map.Entry<String, Set<String>> stringSetEntry : supportedScripts.entrySet()) {
            System.out.print("scripts: " + stringSetEntry.getKey());
            System.out.println(" languages: " + stringSetEntry.getValue().toString());
        }
        System.out.println();
    }

    /**
     * Usage
     */
    private static void usage() {
        System.out.println("Usage: java -jar Rosette-API-Example.jar -Drosette.api.key=your-api-key");
    }

    /**
     * Pings Rosette API.
     */
    private static void doPing() {
        try {
            PingResponse pingResponse = rosetteAPI.ping();
            print(pingResponse);
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints ping response.
     * @param pingResponse
     */
    private static void print(PingResponse pingResponse) {
        System.out.printf("Message: %s\tTime: %s\n",
                pingResponse.getMessage(),
                new Date(pingResponse.getTime()).toString()
        );
        System.out.println();
    }

    /**
     * Sends info request.
     */
    private static void doGetInfo() {
        try {
            InfoResponse infoResponse = rosetteAPI.info();
            print(infoResponse);
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints info response.
     * @param infoResponse
     */
    private static void print(InfoResponse infoResponse) {
        System.out.printf("Name: %s\tVersion: %s\tBuild number: %s\tBuild time: %s\n",
                infoResponse.getName(),
                infoResponse.getVersion(),
                infoResponse.getBuildNumber(),
                infoResponse.getBuildTime()
        );
        System.out.println();
    }

    /**
     * Sends NameMatcherRequest.
     * @param name1
     * @param name2
     */
    private static void doNameMatcher(String name1, String name2) {
        try {
            NameMatcherRequest request = new NameMatcherRequest(new Name(name1), new Name(name2));
            NameMatcherResponse response = rosetteAPI.matchName(request);
            print(request, response);
        } catch (RosetteAPIException | RosetteAPIParameterException | IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints NameMatcherResponse.
     * @param request
     * @param response
     */
    private static void print(NameMatcherRequest request, NameMatcherResponse response) {
        System.out.println(response.getRequestId());
        NameMatcherResult result = response.getResult();
        System.out.printf("%s\t%s\t%f\n", request.getName1().getText(), request.getName2().getText(), result.getScore());
        System.out.println();
    }

    /**
     * Sends URL category request
     * @param url
     */
    private static void doCategories(URL url) {
        doCategories(url, null);
    }

    /**
     * Sends file category request
     * @param file
     */
    private static void doCategories(InputStream file) {
        doCategories(file, null);
    }

    /**
     * Sends string category request
     * @param text
     */
    private static void doCategories(String text) {
        doCategories(text, null);
    }

    /**
     * Sends URL category request with options.
     * @param url
     * @param options
     */
    private static void doCategories(URL url, CategoryOptions options) {
        try {
            CategoryResponse categoryResponse = rosetteAPI.getCategories(url, null, options);
            print(categoryResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends InputStream category request with options.
     * @param inputStream
     * @param options
     */
    private static void doCategories(InputStream inputStream, CategoryOptions options) {
        try {
            CategoryResponse categoryResponse = rosetteAPI.getCategories(inputStream, null, options);
            print(categoryResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends string category request with options.
     * @param text
     * @param options
     */
    private static void doCategories(String text, CategoryOptions options) {
        try {
            CategoryResponse categoryResponse = rosetteAPI.getCategories(text, null, options);
            print(categoryResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints CategoryResponse.
     * @param categoryResponse
     */
    private static void print(CategoryResponse categoryResponse) {
        System.out.println(categoryResponse.getRequestId());
        for (Category category : categoryResponse.getCategories()) {
            System.out.printf("%s\t%f\t%s\n",
                    category.getLabel(),
                    category.getConfidence(),
                    category.getExplanations() == null ? "" : category.getExplanations());
        }
        System.out.println();
    }

    /**
     * Sends file Sentiment request.
     * @param file
     */
    private static void doSentiment(InputStream file) {
        doSentiment(file, null, null);
    }

    /**
     * Sends file Sentiment request with language option.
     * @param file
     * @param language
     */
    private static void doSentiment(InputStream file, String language) {
        doSentiment(file, language, null);
    }

    /**
     * Sends file Sentiment request with language and SentimentOptions.
     * @param file
     * @param language
     * @param options
     */
    private static void doSentiment(InputStream file, String language, SentimentOptions options) {
        try {
            SentimentResponse sentimentResponse = rosetteAPI.getSentiment(file, language, options);
            print(sentimentResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends string Sentiment request.
     * @param text
     */
    private static void doSentiment(String text) {
        doSentiment(text, null);
    }

    /**
     * Sends string Sentiment request with SentimentOptions.
     * @param text
     * @param options
     */
    private static void doSentiment(String text, SentimentOptions options) {
        try {
            SentimentResponse sentimentResponse = rosetteAPI.getSentiment(text, null, options);
            print(sentimentResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL Sentiment request.
     * @param url
     */
    private static void doSentiment(URL url) {
        doSentiment(url, null);
    }

    /**
     * Sends URL Sentiment request with SentimentOptions.
     * @param url
     * @param options
     */
    private static void doSentiment(URL url, SentimentOptions options) {
        try {
            SentimentResponse sentimentResponse = rosetteAPI.getSentiment(url, null, options);
            print(sentimentResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints SentimentResponse.
     * @param sentimentResponse
     */
    private static void print(SentimentResponse sentimentResponse) {
        System.out.println(sentimentResponse.getRequestId());
        for (Sentiment sentiment : sentimentResponse.getSentiment()) {
            System.out.printf("%s\t%f\n", sentiment.getLabel(), sentiment.getConfidence());
            if (sentiment.getExplanations() != null) {
                for (String explanation : sentiment.getExplanations()) {
                    System.out.printf("  explanation: %s\n", explanation);
                }
            }
        }
        System.out.println();
    }

    /**
     * Sends file LinkedEntity request.
     * @param file
     */
    private static void doLinkedEntity(InputStream file) {
        try {
            LinkedEntityResponse entityResponse = rosetteAPI.getLinkedEntity(file, null);
            print(entityResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints LinkedEntityResponse.
     * @param entityResponse
     */
    private static void print(LinkedEntityResponse entityResponse) {
        System.out.println(entityResponse.getRequestId());
        for (LinkedEntity entity : entityResponse.getEntities()) {
            System.out.printf("%s\t%d\t%s\t%f\n",
                    entity.getEntityId(),
                    entity.getIndocChainId(),
                    entity.getMention(),
                    entity.getConfidence());
        }
        System.out.println();
    }

    /**
     * Sends string LinkedEntity request.
     * @param text
     */
    private static void doLinkedEntity(String text) {
        try {
            LinkedEntityResponse entityResponse = rosetteAPI.getLinkedEntity(text, null, null);
            print(entityResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL LinkedEntity request.
     * @param url
     */
    private static void doLinkedEntity(URL url) {
        try {
            LinkedEntityResponse entityResponse = rosetteAPI.getLinkedEntity(url, null);
            print(entityResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file Entity request.
     * @param file
     */
    private static void doEntity(InputStream file) {
        try {
            EntityResponse entityResponse = rosetteAPI.getEntity(file, null, null);
            print(entityResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints EntityResponse.
     * @param entityResponse
     */
    private static void print(EntityResponse entityResponse) {
        System.out.println(entityResponse.getRequestId());
        for (ExtractedEntity entity : entityResponse.getEntities()) {
            System.out.printf("%s\t%s\t%d\t%s\t%f\t%d\n",
                    entity.getNormalized(),
                    entity.getType(),
                    entity.getCount(),
                    entity.getMention(),
                    entity.getConfidence(),
                    entity.getIndocChainId());
        }
    }

    /**
     * Sends string Entity request.
     * @param text
     */
    private static void doEntity(String text) {
        try {
            EntityResponse entityResponse = rosetteAPI.getEntity(text, null, null);
            print(entityResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL Entity request.
     * @param url
     */
    private static void doEntity(URL url) {
        try {
            EntityResponse entityResponse = rosetteAPI.getEntity(url, null, null);
            print(entityResponse);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file Language request.
     * @param file
     */
    private static void doLanguage(InputStream file) {
        try {
            LanguageResponse response = rosetteAPI.getLanguage(file, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints LanguageResponse.
     * @param response
     */
    private static void print(LanguageResponse response) {
        System.out.println(response.getRequestId());
        for (LanguageDetectionResult ldr : response.getLanguageDetections()) {
            System.out.printf("%s\t%f\n", ldr.getLanguage(), ldr.getConfidence());
        }
        System.out.println();
    }

    /**
     * Sends string Language request.
     * @param text
     */
    private static void doLanguage(String text) {
        try {
            LanguageResponse response = rosetteAPI.getLanguage(text, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL Language request.
     * @param url
     */
    private static void doLanguage(URL url) {
        try {
            LanguageResponse response = rosetteAPI.getLanguage(url, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file Morphology request.
     * @param file
     */
    private static void doMorphology(InputStream file) {
        try {
            MorphologyResponse response = rosetteAPI.getMorphology(RosetteAPI.MorphologicalFeature.COMPLETE, file, null, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends string Morphology request.
     * @param text
     */
    private static void doMorphology(String text) {
        try {
            MorphologyResponse response = rosetteAPI.getMorphology(RosetteAPI.MorphologicalFeature.COMPLETE, text, null, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL Morphology request.
     * @param url
     */
    private static void doMorphology(URL url) {
        try {
            MorphologyResponse response = rosetteAPI.getMorphology(RosetteAPI.MorphologicalFeature.COMPLETE, url, null, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints MorphologyResponse.
     * @param response
     */
    private static void print(MorphologyResponse response) {
        System.out.println(response.getRequestId());
        String result = "===== posTags =====\n";
        if (response.getPosTags() != null) {
            for (PartOfSpeech partOfSpeech : response.getPosTags()) {
                result += partOfSpeech.getText() + "\t" + partOfSpeech.getPos() + "\n";
            }
        }
        result += "===== lemmas =====\n";
        if (response.getLemmas() != null) {
            for (Lemma lemma : response.getLemmas()) {
                result += lemma.getText() + "\t" + lemma.getLemma() + "\n";
            }
        }
        result += "===== compounds =====\n";
        if (response.getCompounds() != null) {
            for (Decompounding compoud : response.getCompounds()) {
                result += compoud.getText() + "\t" + compoud.getCompoundComponents() + "\n";
            }
        }
        result += "===== hanReadings =====\n";
        if (response.getHanReadings() != null) {
            for (HanReadings reading : response.getHanReadings()) {
                result += reading.getText() + "\t" + reading.getHanReadings() + "\n";
            }
        }
        result += "\n";
        System.out.println(result);
    }

    /**
     * Sends name translation request.
     * @param name
     * @param targetLanguage
     */
    private static void doNameTranslation(String name, String targetLanguage) {
        try {
            NameTranslationRequest nameTranslationRequest = new NameTranslationRequest(name, null, null, null, null, targetLanguage, null, null);
            NameTranslationResponse response = rosetteAPI.translateName(nameTranslationRequest);
            print(name, response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints NameTranslationResponse.
     * @param name
     * @param response
     */
    private static void print(String name, NameTranslationResponse response) {
        System.out.println(response.getRequestId());
        TranslatedNameResult result = response.getResult();
        System.out.println("name: " + name + ", "
                + "sourceScript: " + result.getSourceScript() + ", "
                + "sourceLanguageOfOrigin: " + result.getSourceLanguageOfOrigin() + ", "
                + "sourceLanguageOfUse: " + result.getSourceLanguageOfUse() + ", "
                + "translation: " + result.getTranslation() + ", "
                + "targetLanguage: " + result.getTargetLanguage() + ", "
                + "targetScript: " + result.getTargetScript() + ", "
                + "targetScheme: " + result.getTargetScheme() + ", "
                + "confidence: " + result.getConfidence());
        System.out.println();
    }

    /**
     * Sends file token request.
     * @param file
     */
    private static void doTokens(InputStream file) {
        try {
            TokenResponse response = rosetteAPI.getTokens(file, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file token request.
     * @param file
     */
    private static void doTokens(InputStream file, String languageCode) {
        try {
            TokenResponse response = rosetteAPI.getTokens(file, languageCode);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints TokensResponse.
     * @param response
     */
    private static void print(TokenResponse response) {
        System.out.println(response.getRequestId());
        for (String token : response.getTokens()) {
            System.out.println(token);
        }
        System.out.println();
    }

    /**
     * Sends string token request.
     * @param text
     */
    private static void doTokens(String text) {
        try {
            TokenResponse response = rosetteAPI.getTokens(text, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL token request.
     * @param url
     */
    private static void doTokens(URL url) {
        try {
            TokenResponse response = rosetteAPI.getTokens(url, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file sentences request.
     * @param file
     */
    private static void doSentences(InputStream file) {
        try {
            SentenceResponse response = rosetteAPI.getSentences(file, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file sentences request.
     * @param file
     */
    private static void doSentences(InputStream file, String languageCode) {
        try {
            SentenceResponse response = rosetteAPI.getSentences(file, languageCode);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Prints TokensResponse.
     * @param response
     */
    private static void print(SentenceResponse response) {
        System.out.println(response.getRequestId());
        for (String sentence : response.getSentences()) {
            System.out.println(sentence);
        }
        System.out.println();
    }

    /**
     * Sends string sentences request.
     * @param text
     */
    private static void doSentences(String text) {
        try {
            SentenceResponse response = rosetteAPI.getSentences(text, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends file sentences request.
     * @param text
     */
    private static void doSentences(String text, String languageCode, InputUnit unit) {
        try {
            SentenceResponse response = rosetteAPI.getSentences(text, languageCode, unit);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Sends URL sentences request.
     * @param url
     */
    private static void doSentences(URL url) {
        try {
            SentenceResponse response = rosetteAPI.getSentences(url, null);
            print(response);
        } catch (RosetteAPIException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
