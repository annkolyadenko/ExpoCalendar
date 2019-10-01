package ua.com.expo.util.pagination;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.command.client.CommandEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class PaginationUtil {

    private static final Logger LOGGER = LogManager.getLogger(PaginationUtil.class.getName());

    private PaginationUtil() {
    }

    private static class Holder {
        static final PaginationUtil INSTANCE = new PaginationUtil();
    }

    public static PaginationUtil getInstance() {
        return Holder.INSTANCE;
    }

    public void doPagination(Integer limit, Integer currentPage, Integer rows, HttpServletRequest request) {
        //TODO VALIDATOR
        if (Objects.nonNull(rows)) {
            int numberOfPages = rows / limit;
            LOGGER.debug("NUMBER OF PAGES :" + numberOfPages);
            if (rows % limit > 0) {
                ++numberOfPages;
                LOGGER.debug("++NUMBER OF PAGES :" + numberOfPages);
            }
            request.setAttribute("numberOfPages", numberOfPages);
            LOGGER.debug("NUMBER OF PAGES :" + numberOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("limit", limit);
        }
    }
}
