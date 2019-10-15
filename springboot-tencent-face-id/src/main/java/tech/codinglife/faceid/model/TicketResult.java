package tech.codinglife.faceid.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019-09-18
 * Time: 17:43
 */
public class TicketResult extends Base {
    private String transactionTime;
    private List<Ticket> tickets;

    public TicketResult() {
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    private static class Ticket {
        private String value;
        private String expire_in;
        private String expire_time;

        public Ticket() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getExpire_in() {
            return expire_in;
        }

        public void setExpire_in(String expire_in) {
            this.expire_in = expire_in;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }
    }
}
