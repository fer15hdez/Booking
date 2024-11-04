package com.booking;

import com.booking.domain.FunctionType;
import com.booking.domain.FunctionTypeRepository;
import com.booking.domain.RoomType;
import com.booking.domain.RoomTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			FunctionTypeRepository functionTypeRepository,
			RoomTypeRepository roomTypeRepository
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

		};
	}

}
