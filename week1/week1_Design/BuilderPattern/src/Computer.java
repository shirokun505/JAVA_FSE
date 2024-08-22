public class Computer {
    private final String CPU;
    private final String RAM;
    private final String storage;
    private final boolean isGraphicsCard;
    private final boolean isBluetooth;
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.isGraphicsCard = builder.isGraphicsCard;
        this.isBluetooth = builder.isBluetooth;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage +
               ", isGraphicsCard=" + isGraphicsCard + ", isBluetooth=" + isBluetooth + "]";
    }
    public static class Builder {
        private final String CPU;
        private final String RAM;
        private String storage = "256GB SSD";
        private boolean isGraphicsCard = false;
        private boolean isBluetooth = false;
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGraphicsCard(boolean isGraphicsCard) {
            this.isGraphicsCard = isGraphicsCard;
            return this;
        }

        public Builder setBluetooth(boolean isBluetooth) {
            this.isBluetooth = isBluetooth;
            return this;
        }
        public Computer build() {
            return new Computer(this);
        }
    }
}
