package view;

import domain.entity.Post;

import java.io.PrintStream;
import java.util.List;

public class SearchEngineView {
    private final PrintStream out = new PrintStream(System.out);

    public void inputMenu() {
        out.println("************ Lucene Search ************");
        out.println("** 1. indexing, 2. searching, 3. end **");
        out.println("***************************************");
        out.print("* 메뉴를 입력해 주세요 > ");
    }

    public void outputResult(List<Post> posts) {
        out.println("************** 검색 결과 ***************");
        if (posts.isEmpty()) {
            out.println(" * 검색 결과가 없습니다.");
        } else {
            posts.forEach(out::println);
        }
        out.println("***************************************");
    }

    public void inputKeyword() {
        out.print("* 검색어를 입력해 주세요 > ");
    }

    public void startIndexing() {
        out.println("* Index 시작");
    }

    public void endIndexing() {
        out.println("* Index 완료");
    }
}
