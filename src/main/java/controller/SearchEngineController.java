package controller;

import domain.entity.Post;
import domain.service.PostIndexer;
import domain.service.PostSearcher;
import util.ExceptionLogging;
import view.SearchEngineView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static util.EngineConstants.Menu.*;

public class SearchEngineController {
    private final SearchEngineView view = new SearchEngineView();
    private final PostSearcher postSearcher = new PostSearcher();

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            ExceptionLogging.log();
            while (true) {
                int menu = inputMenu(br);

                switch (menu) {
                    case INDEX_MENU:
                        view.startIndexing();
                        PostIndexer.index();
                        view.endIndexing();
                        break;
                    case SEARCH_MENU:
                        postSearcher.setKeyword(inputKeyword(br));
                        List<Post> posts = postSearcher.getResult();
                        view.outputResult(posts);
                        break;
                    case END_SERVICE:
                        return;
                    default:
                        throw new IOException();
                }
            }
        } catch (IOException | NumberFormatException e) {
            ExceptionLogging.log(e);
        }
    }

    private int inputMenu(BufferedReader br) throws IOException, NumberFormatException {
        view.inputMenu();

        String input = br.readLine();
        validateInput(input);

        int menu = Integer.parseInt(input);
        validateNumber(menu);

        return menu;
    }

    private String inputKeyword(BufferedReader br) throws IOException {
        view.inputKeyword();
        return br.readLine();
    }

    private void validateNumber(int menu) throws IOException {
        if (menu != INDEX_MENU && menu != SEARCH_MENU && menu != END_SERVICE) {
            throw new IOException(NUM_ERROR_MSG);
        }
    }

    private void validateInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}
