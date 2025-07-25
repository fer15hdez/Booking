package com.booking;

import com.booking.data.structures.Interval;
import com.booking.data.structures.IntervalTree;
import com.booking.data.structures.Node;
import com.booking.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);

		List<Interval> intervals = new ArrayList<>();

		intervals.add(new Interval(1, 5));
		intervals.add(new Interval(3, 7));
		intervals.add(new Interval(2, 4));
		intervals.add(new Interval(6, 9));
		intervals.add(new Interval(8, 12));

		IntervalTree tree = new IntervalTree();
		Node root = tree.buildTree(intervals);

		System.out.println("IntervalTree: " + tree);
		System.out.println("Node root: " + root);

		Interval x = new Interval(2, 6);
		tree.search(root, x);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			FunctionTypeRepository functionTypeRepository,
			RoomTypeRepository roomTypeRepository,
			BookingRepository bookingRepository,
			CustomerRepository customerRepository,
			ResourceRepositoy resourceRepositoy,
			RoomRepository roomRepository
			){

		return args -> {
			for (int i = 0; i < 10; i++){
				var functionType = FunctionType.builder()
						.name("Trabajo de espalda: " +  i)
						.description("Trabaja los musculos bajos")
						.build();
				functionTypeRepository.save(functionType);
			}


			for (int i = 0; i < 10; i++){
				var roomType = RoomType.builder()
						.name("Masajes " + i)
						.description("Masaje integral")
						.build();

				roomTypeRepository.save(roomType);
			}
//
//			customerRepository.save(new Customer(1));
//			resourceRepositoy.save(new Resource(1));


			for (int i = 0; i < 50; i++) {
            /*RoomType roomType = RoomType.builder()
                    .name("Tipo de Cuarto " + i)
                    .build();*/
				RoomType roomType = new RoomType();
				roomType.setName("Tipo de Cuarto " + i);
				roomTypeRepository.save(roomType);
			}

			int j = 1;
			for (int i = 1; i < 100; i++) {
				Room room = Room.builder()
						.name("Cuarto " + i)
						.width(50 + i)
						.length(60 + i)
						.type(new RoomType(j))
						.build();

				if (i < 49)
					j++;

				roomRepository.save(room);
			}


		};
	}



}
