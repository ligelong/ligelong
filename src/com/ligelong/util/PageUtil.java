/*
 * Created on 2011-08-20
 */
package com.ligelong.util;

/**
 * <code>PageUtil</code>
 *
 * @author David Gong
 */
public class PageUtil {
    /**
     * 
     * @param ItemNumber
     * @param pageNum
     * @param PageSize
     * @return PageScrollInfo
     */
    public static PageScrollInfo getPageScroll(int ItemNumber, int pageNum, int PageSize) {
        int pageNumber = pageNum;
        PageScrollInfo ret = new PageScrollInfo();
        if (ItemNumber <= PageSize) {
            pageNumber = 1;
        }
        ret.itemNumber = ItemNumber;
        ret.currentPageNumber = pageNumber;
        ret.backPageNumber = ret.currentPageNumber - 1;
        ret.backFivePageNumber = ret.currentPageNumber - 5;
        ret.nextPageNumber = ret.currentPageNumber + 1;
        ret.fivePageNumber = ret.currentPageNumber + 5;
        ret.pageNumber = ((ret.itemNumber - 1) / PageSize) + 1;
        if (ret.pageNumber < 1)
            ret.pageNumber = 1;
        ret.currentItemStart = (ret.currentPageNumber - 1) * PageSize;
        ret.currentItemNumber = PageSize;
        ret.backPage = ret.currentPageNumber > 1;
        ret.backFivePage = (ret.currentPageNumber - 5) >= 1;
        ret.nextPage = ret.currentPageNumber < ret.pageNumber;
        ret.nextFivePage = (ret.currentPageNumber + 5) <= ret.pageNumber;
        return ret;
        
    }

    /**
     * 
     * <code>PageScrollInfo</code>
     *
     * @author David Gong
     */
    public static class PageScrollInfo {
        // 
        int itemNumber = 0;
        // 
        int pageNumber = 0;
        // 
        int currentPageNumber = 0;
        // 
        int nextPageNumber = 0;
        // 
        int fivePageNumber = 0;
        // 
        int backPageNumber = 0;
        // 
        int backFivePageNumber = 0;
        // 
        int currentItemStart = 0;
        // 
        int currentItemNumber = 0;
        // 
        boolean backPage = false;
        // 
        boolean nextPage = false;
        // 
        boolean nextFivePage = false;
        // 
        boolean backFivePage = false;

        /**
         * @return this itemNumber
         */
        public int getItemNumber() {
            return this.itemNumber;
        }

        /**
         * @return this pageNumber
         */
        public int getPageNumber() {
            return this.pageNumber;
        }

        /**
         * @return this currentPageNumber
         */
        public int getCurrentPageNumber() {
            return this.currentPageNumber;
        }

        /**
         * @return this nextPageNumber
         */
        public int getNextPageNumber() {
            return this.nextPageNumber;
        }

        /**
         * @return this fivePageNumber
         */
        public int getFivePageNumber() {
            return this.fivePageNumber;
        }

        /**
         * @return this backPageNumber
         */
        public int getBackPageNumber() {
            return this.backPageNumber;
        }

        /**
         * @return this backFivePageNumber
         */
        public int getBackFivePageNumber() {
            return this.backFivePageNumber;
        }

        /**
         * @return this nextPage
         */
        public boolean hasNextPage() {
            return this.nextPage;
        }

        /**
         * @return this nextFivePage
         */
        public boolean hasNextFivePage() {
            return this.nextFivePage;
        }

        /**
         * @return this backPage
         */
        public boolean hasBackPage() {
            return this.backPage;
        }

        /**
         * @return this backFivePage
         */
        public boolean hasBackFivePage() {
            return this.backFivePage;
        }

        /**
         * @return this currentItemStart
         */
        public int getCurrentItemStart() {
            return this.currentItemStart;
        }

        /**
         * @return this currentItemNumber
         */
        public int getCurrentItemNumber() {
            return this.currentItemNumber;
        }
        
        /*
         * (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        public String toString() {
            return "[itemNumber = " + this.itemNumber + "][pageNumber = "
                    + this.pageNumber + "][currentPageNumber = "
                    + this.currentPageNumber + "][nextPageNumber = "
                    + this.nextPageNumber + "][fivePageNumber = "
                    + this.fivePageNumber + "][backPageNumber = "
                    + this.backPageNumber + "][backFivePageNumber = "
                    + this.backFivePageNumber + "][currentItemStart = "
                    + this.currentItemStart + "][currentItemNumber = "
                    + this.currentItemNumber + "][backPage = " + this.backPage
                    + "][nextPage = " + this.nextPage + "][nextFivePage = "
                    + this.nextFivePage + "][backFivePage = "
                    + this.backFivePage + "]";
        }
    }
}