import brickset.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


public class legosetstreamek {

  private List<LegoSet> legoset = new LegoSetRepository().getAll();

  /**
  * Egy keszlet neve ami tartalmazza a "star" szot.
  * @return true / false
  */

  public boolean legoSetNameWithStar(){
    return legoset.stream().anyMatch(s -> s.getName().toLowerCase().contains("star"));
  }

  /**
   * Az összes különböző téma száma
   * @return the number of the distinct tags
   */
  public long numberOfAllKindsOfThemes() {
    return legoset.stream().flatMap(themes -> themes.getTheme().lines()).distinct().count();
  }
  /**
   * Visszaadja az összes legó számát
   * @return The number of pieces
   */

  public long numberOfLegoPieces() {
    return legoset.stream().map(pieces -> pieces.getPieces()).filter(Objects::nonNull).reduce(0, Integer::sum);
  }
  /**
   * Vissza adja témánként a lego szettek számát.
   * @return number of lego sets grouped by themes
   */
  public Map<String, Long> numberOfSetsGroupedByTheme() {
    Map<String, Long> numoflegos =
            legoset.stream().collect(groupingBy(LegoSet::getTheme, counting()));
    return numoflegos;
  }
  /**
   * Vissza adja a "Miscellaneous" themájú legok tagjainak nevét és darabszámát.
   * @return "Miscellaneous" lego sets with theme
   */
  public Map<String, Long> numberOfTagsPerNames(){
    Map<String, Long> tagspernames =
            legoset.stream().filter(theme -> theme.getTheme().equals("Miscellaneous")).collect(groupingBy(LegoSet::getName, counting()));
    return tagspernames;
  }


    public static void main(String[] args) {

        var legoset = new legosetstreamek();
      System.out.println(legoset.legoSetNameWithStar());
      System.out.println(legoset.numberOfAllKindsOfThemes());
      System.out.println(legoset.numberOfLegoPieces());
      System.out.println(legoset.numberOfSetsGroupedByTheme());
      System.out.println(legoset.numberOfTagsPerNames());

  }
}

