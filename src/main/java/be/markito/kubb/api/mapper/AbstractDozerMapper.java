package be.markito.kubb.api.mapper;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an abstract class used to auto wire the Dozer mapper.
 * This way subclasses don't have to wire it themselves.
 *
 * @author MArKiTo
 * @see <a href="http://dozer.sourceforge.net">Dozer</a>
 */
public abstract class AbstractDozerMapper<D, W> {

    /**
     * The dozer bean mapper that can be used by all classes inheriting from this class.
     */
    @Autowired
    private Mapper dozerBeanMapper;

    protected Mapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    /**
     * Maps a single object of class D to a single object of class W.
     * When NULL value is passed in, no mapping takes place and NULL value is returned.
     *
     * @param dbInput The input object that will be converted.
     * @return The converted object that can be exposed to the client.
     */
    public W mapToWeb(D dbInput) {
        if (dbInput != null) {
            return doMapDbToWeb(dbInput);
        } else {
            return null;
        }
    }

    /**
     * Maps a {@link java.util.List} of objects of class D to a {@link java.util.List} of objects of class W.
     * When NULL value or an empty {@link java.util.List} is passed in, no mapping takes place and NULL value is returned.
     *
     * @param dbInputs The {@link java.util.List} of input objects that will be converted.
     * @return a {@link java.util.List} of converted objects that can be exposed to the client.
     */
    public List<W> mapToWeb(List<D> dbInputs) {
        if (dbInputs == null) {
            return null;
        }
        if (CollectionUtils.isNotEmpty(dbInputs)) {
            List<W> result = new ArrayList<>();
            for (D dbInput : dbInputs) {
                W webOutput = doMapDbToWeb(dbInput);
                result.add(webOutput);
            }
            return result;
        } else {
            return new ArrayList<W>();
        }
    }

    /**
     * An implementation of this method should perform a conversion from the D object
     * to an object of type W.
     *
     * @param dbInput The input object that needs to be converted.
     * @return The converted object. The member variables in this object should have
     * been instrumented using the values in the dbInput object.
     */
    protected W doMapDbToWeb(D dbInput) {
        throw new UnsupportedOperationException();
    }
}
