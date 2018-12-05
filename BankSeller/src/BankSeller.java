import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

@ScriptMeta(version = 0.01, name = "BankSeller", category = ScriptCategory.MONEY_MAKING, developer = "buracc", desc = "Kanker")
public class BankSeller extends Script {

    boolean deposited = false;
    boolean done = true;

    @Override
    public int loop() {
        if (done) {
            if (Inventory.isFull()) {
                done = false;
                return 50;
            }

            if (!Bank.isOpen()) {
                if (Bank.open()) {
                    Time.sleepUntil(Bank::isOpen, 1400);
                }
            }

            if (!deposited && Bank.depositInventory()) {
                deposited = true;
                Time.sleepUntil(Inventory::isEmpty, 1400);
            }

            if (!Bank.getWithdrawMode().equals(Bank.WithdrawMode.NOTE)) {
                if (Bank.setWithdrawMode(Bank.WithdrawMode.NOTE)) {
                    Time.sleep(100);
                }
            }

            Item item = Bank.getFirst(x -> x != null && x.isExchangeable());
            Time.sleep(50);
            if (Bank.withdrawAll(item.getId())) {
                return 50;
            }
        } else {
            deposited = false;
            Bank.close();
            if (Inventory.getFreeSlots() > 26) {
                done = true;
                return 600;
            }
            if (Npcs.getNearest(2148).interact("Exchange")) {
                Time.sleepUntil(GrandExchange::isOpen, 1400);
            }

            if (GrandExchange.getFirstEmpty() != null) {
                Item item = Inventory.getFirst(x -> x != null && x.isExchangeable());
                Log.info("Selling item " + item.getName());
                if (GrandExchange.createOffer(RSGrandExchangeOffer.Type.SELL)) {
                    Time.sleep(600);
                }

                if (GrandExchangeSetup.setItem(item.getId())) {
                    Time.sleep(600);
                }

                if (GrandExchangeSetup.setPrice(1)) {
                    Time.sleep(600);
                }

                if (GrandExchangeSetup.confirm()) {
                    Time.sleep(600);
                }

            } else {
                Log.info("Collecting");
                GrandExchange.collectAll();
                return 600;
            }
        }
        return 600;
    }

    public boolean isTraversing() {
        return Movement.getDestination() != null && Movement.getDestination().distance() >= Random.nextInt(1, 5) && Players.getLocal().isMoving();
    }
}
