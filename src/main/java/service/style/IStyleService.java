package service.style;

import model.Style;

import java.util.List;

public interface IStyleService {
    List<Style> showAll();

    Style findByID(int id);

    boolean create(Style style);

    boolean update(Style style);

    boolean delete(int id);
}
