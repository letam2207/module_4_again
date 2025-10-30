package com.example.quan_ly_cau_thu_thymeleaf.repository;

import com.example.quan_ly_cau_thu_thymeleaf.entity.Player;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository implements IPlayerRepository {
    private static final List<Player> playerList = new ArrayList<>();

    static {
        playerList.add(new Player(1, "Lionel Messi", LocalDate.of(1987, 6, 24),
                "Huyền thoại của Barcelona, chủ nhân 8 Quả bóng vàng, được xem là cầu thủ vĩ đại nhất lịch sử bóng đá.",
                "Tiền đạo",
                "https://fifpro.org/media/5chb3dva/lionel-messi_imago1019567000h.jpg?rxy=0.32986930611281567,0.18704579979466449&rnd=133378758718600000"));

        playerList.add(new Player(2, "Kylian Mbappé", LocalDate.of(1998, 12, 20),
                "Ngôi sao người Pháp của PSG và Real Madrid, nổi tiếng với tốc độ và kỹ thuật siêu hạng, vô địch World Cup 2018.",
                "Tiền đạo cánh",
                "https://img.uefa.com/imgml/TP/players/1/2026/cutoff/250076574.webp"));

        playerList.add(new Player(3, "Luka Modrić", LocalDate.of(1985, 9, 9),
                "Nhạc trưởng của Real Madrid, chủ nhân Quả bóng vàng 2018, dẫn dắt Croatia vào chung kết World Cup.",
                "Tiền vệ trung tâm",
                "https://assets.realmadrid.com/is/image/realmadrid/MODRIC_EQUIPO_CARITA_550X650%20%E2%80%93%201?$Mobile$&fit=wrap&wid=312"));

        playerList.add(new Player(4, "Virgil van Dijk", LocalDate.of(1991, 7, 8),
                "Đội trưởng Liverpool và tuyển Hà Lan, nổi tiếng với khả năng phòng ngự chắc chắn và phong thái điềm tĩnh.",
                "Trung vệ",
                "https://cdn-img.thethao247.vn/storage/files/ctvqt/2024/12/15/tin-13-154004avatar.jpg"));

        playerList.add(new Player(5, "Kevin De Bruyne", LocalDate.of(1991, 6, 28),
                "Nhạc trưởng của Manchester City, nổi tiếng với những đường chuyền chính xác và tầm nhìn chiến thuật xuất sắc.",
                "Tiền vệ tấn công",
                "https://upload.wikimedia.org/wikipedia/commons/9/97/Kevin_De_Bruyne-December_2021.png"));
    }


        @Override
    public List<Player> findAll() {
        return playerList;
    }

    @Override
    public Player findById(int id) {
        for (Player player : playerList) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Player player : playerList) {
            if (player.getId() == id) {
                playerList.remove(player);
                break;
            }
        }
    }

    @Override
    public boolean add(Player player) {
        return playerList.add(player);
    }
}
