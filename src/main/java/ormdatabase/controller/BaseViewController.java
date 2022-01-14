package ormdatabase.controller;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.util.Pair;
import ormdatabase.entity.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BaseViewController extends InitRecordViewController {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public int currentVersion = 1;

    protected void viewRecord(Record record) {
        currentVersion = record.getShimStackSetList().size();
        id.setText(Objects.isNull(record.getId()) ? "Id" : String.valueOf(record.getId()));
        name.setText(record.getName());
        car.setText(record.getCar());
        date.setValue(Objects.isNull(record.getDate()) ? LocalDate.now() : LocalDate.parse(record.getDate(), formatter));
        phone.setText(record.getPhone());
        city.setText(record.getCity());
        favorites.setSelected(record.isFavorites());
        viewVersion(record, currentVersion);
    }

    private void viewVersion(Record record, int targetVersion) {
        ShimStackSet shimStackSet = record.getShimStackSetList().get(targetVersion - 1);
        String versionAmount = String.valueOf(record.getShimStackSetList().size());
        version.setText(String.valueOf(targetVersion).concat("/").concat(versionAmount));
        versionDate.setValue(shimStackSet.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        comment.setText(shimStackSet.getComment());
        author.setText(shimStackSet.getAuthor());
        type.getSelectionModel().select(shimStackSet.getType());
        setType(shimStackSet.getType());
        currentVersion = targetVersion;
        viewTableValues(record, targetVersion);
    }

    protected void viewPreviousVersion(Record record) {
        if (currentVersion - 1 > 0) {
            saveVersion(record);
            int targetVersion = currentVersion - 1;
            viewVersion(record, targetVersion);
        }
    }

    protected void viewNextVersion(Record record) {
        int versionAmount = record.getShimStackSetList().size();
        if (currentVersion + 1 <= versionAmount) {
            saveVersion(record);
            int targetVersion = currentVersion + 1;
            viewVersion(record, targetVersion);
        }
    }

    private void viewTableValues(Record record, Integer targetVersion) {
        List<Pair<TableView<Shim>, TableView<Shim>>> tableList =
                Arrays.asList(new Pair<>(reboundTable1, compressionTable1),
                        new Pair<>(reboundTable2, compressionTable2),
                        new Pair<>(reboundTable3, compressionTable3),
                        new Pair<>(reboundTable4, compressionTable4)
                );
        ShimStackSet targetShimStackSet = record.getShimStackSetList().get(targetVersion - 1);
        List<Shim> reboundList = List.of(new Shim("1", "1", "1"));
        List<Shim> compressionList = List.of(new Shim("1", "1", "1"));
        for (int i = 0; i < targetShimStackSet.getTypeNumber(); i++) {
            if (targetShimStackSet.getShimStackList().size() > 0) {
                reboundList = targetShimStackSet.getShimStackList().get(i).getReboundStack().getStack();
                compressionList = targetShimStackSet.getShimStackList().get(i).getCompressionStack().getStack();
            }
            tableList.get(i).getKey().setItems(FXCollections.observableArrayList(reboundList));
            tableList.get(i).getValue().setItems(FXCollections.observableArrayList(compressionList));
        }
    }

    protected void deleteVersion(Record record) {
        int versionAmount = record.getShimStackSetList().size();
        int targetVersion;
        if (versionAmount > 1) {
            if (currentVersion == 1) {
                targetVersion = 1;
            } else if (currentVersion == versionAmount) {
                targetVersion = versionAmount - 1;
            } else {
                targetVersion = currentVersion - 1;
            }
            record.deleteVersion(currentVersion - 1);
            viewVersion(record, targetVersion);
        }
    }

    protected void addVersion(Record record) {
        record.setVersion(currentVersion - 1, getCurrentShimStackSet());
        record.addVersion(new ShimStackSet());
        viewVersion(record, record.getShimStackSetList().size());
    }

    private void saveVersion(Record record) {
        record.setVersion(currentVersion - 1, getCurrentShimStackSet());
    }

    private ShimStackSet getCurrentShimStackSet() {
        ShimStackSet shimStackSet = new ShimStackSet();
        shimStackSet.setType(type.getSelectionModel().getSelectedItem());
        Date date = Date.from(versionDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        shimStackSet.setDate(date);
        shimStackSet.setComment(comment.getText());
        shimStackSet.setAuthor(author.getText());
        shimStackSet.setShimStackList(getCurrentShimStackPairList(getActualTables()));
        return shimStackSet;
    }

    private List<StackPair> getCurrentShimStackPairList(List<TableView<Shim>> tableViewList) {
        List<StackPair> stackPairList = new ArrayList<>();
        for (int i = 0; i < tableViewList.size(); i += 2) {
            stackPairList.add(
                    new StackPair(
                            new ReboundStack(new ArrayList<>(tableViewList.get(i).getItems())),
                            new CompressionStack(new ArrayList<>(tableViewList.get(i + 1).getItems())))
            );
        }
        return stackPairList;
    }


    protected void saveObject(Record record) {
        System.out.println("------------------");
        record.setName(name.getText());
        record.setCar(car.getText());
        record.setDate(date.getValue().format(formatter));
        record.setPhone(phone.getText());
        record.setCity(city.getText());
        record.setFavorites(favorites.isSelected());
        saveVersion(record);
    }
}
