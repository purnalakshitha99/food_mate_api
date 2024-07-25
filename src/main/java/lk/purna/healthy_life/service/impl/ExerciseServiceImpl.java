package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.dto.ExerciseDetailsDto;
import lk.purna.healthy_life.controller.dto.ExerciseDto;
import lk.purna.healthy_life.controller.request.ExerciseRq;
import lk.purna.healthy_life.controller.response.ExerciseResponse;
import lk.purna.healthy_life.exception.ExerciseDetailsNotFoundException;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.Exercise;
import lk.purna.healthy_life.model.ExerciseDetails;
import lk.purna.healthy_life.model.TimeType;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.ExerciseDetailsRepository;
import lk.purna.healthy_life.repository.ExerciseRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private  ModelMapper modelMapper;
    private UserRepository userRepository;
    private final ExerciseDetailsRepository exerciseDetailsRepository;
    private final ExerciseRepository exerciseRepository;


//    @Override
//    public ExerciseResponse createExerciseForUser(Long userId, ExerciseDto exerciseDto) throws UserNotFoundException,ExerciseDetailsNotFoundException {
//
//
//        LocalDate currentDate = LocalDate.now();
//
//        User user = userRepository.findById(userId).orElseThrow(
//                ()-> new UserNotFoundException("That User Not in a db")
//        );
//
//        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(exerciseDto.getExerciseId()).orElseThrow(
//                ()-> new ExerciseDetailsNotFoundException("That Exercise Not in a db")
//        );
//
//        Exercise exercise = modelMapper.map(exerciseDto,Exercise.class);
//
//        float ratio;
//        float burnCalories;
//        TimeType timeType = exerciseDto.getTimeType();
//        float timeAmount = Float.valueOf(exerciseDto.getTimeAmount());
//        float hoursInMinute;
//
//        if (timeType == TimeType.HOURS){
//
//            hoursInMinute = timeAmount*60;
//            ratio = hoursInMinute / exerciseDetails.getTimeAmount();
//            burnCalories = ratio * exerciseDetails.getBurnCalories();
//            exercise.setBurnCalories(burnCalories);
//        } else if (timeType == TimeType.MINUTE) {
//
//            ratio = timeAmount / exerciseDetails.getTimeAmount();
//            burnCalories = ratio * exerciseDetails.getBurnCalories();
//            exercise.setBurnCalories(burnCalories);
//
//        }
//
//        exercise.setDate(currentDate);
//        exercise.setName(exerciseDetails.getName());
//
//        user.getExerciseList().add(exercise);
//        exercise.getUserList().add(user);
//        exerciseRepository.save(exercise);
//
//        return modelMapper.map(exercise, ExerciseResponse.class);
//    }

//    @Override
//    public ExerciseResponse createExerciseForUser(Long userId, ExerciseDto exerciseDto) throws UserNotFoundException, ExerciseDetailsNotFoundException {
//
//        LocalDate currentDate = LocalDate.now();
//
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new UserNotFoundException("That User Not in a db")
//        );
//
//        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(exerciseDto.getExerciseId()).orElseThrow(
//                () -> new ExerciseDetailsNotFoundException("That Exercise Not in a db")
//        );
//
//        if (exerciseDetails == null) {
//            throw new ExerciseDetailsNotFoundException("Exercise not found : ");
//        }
//
//        Exercise exercise = modelMapper.map(exerciseDto, Exercise.class);
//
//        float ratio;
//        float burnCalories;
//        TimeType timeType = exerciseDto.getTimeType();
//        float timeAmount = Float.valueOf(exerciseDto.getTimeAmount());
//        float hoursInMinute;
//
//        if (timeType == TimeType.HOURS) {
//            hoursInMinute = timeAmount * 60;
//            ratio = hoursInMinute / exerciseDetails.getTimeAmount();
//            burnCalories = ratio * exerciseDetails.getBurnCalories();
//            exercise.setBurnCalories(burnCalories);
//        } else if (timeType == TimeType.MINUTE) {
//            ratio = timeAmount / exerciseDetails.getTimeAmount();
//            burnCalories = ratio * exerciseDetails.getBurnCalories();
//            exercise.setBurnCalories(burnCalories);
//        }
//
//        exercise.setDate(currentDate);
//        exercise.setName(exerciseDetails.getName());
//
//        // Save the exercise first
//       exercise =  exerciseRepository.save(exercise);
//
//        // Add the saved exercise to the user's exercise list
//        user.getExerciseList().add(exercise);
//
//
//
////        // Update the user entity
//        userRepository.save(user);
//
//        return modelMapper.map(exercise, ExerciseResponse.class);
//    }

//    @Override
//    public ExerciseResponse createExerciseForUser(Long userId, ExerciseDto exerciseDto) throws UserNotFoundException, ExerciseDetailsNotFoundException {
//
//        LocalDate currentDate = LocalDate.now();
//
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new UserNotFoundException("That User Not in a db")
//        );
//
//        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(exerciseDto.getExerciseId()).orElseThrow(
//                () -> new ExerciseDetailsNotFoundException("That Exercise Not in a db")
//        );
//
//        Exercise exercise = modelMapper.map(exerciseDto, Exercise.class);
//
//        float ratio;
//        float burnCalories;
//        TimeType timeType = exerciseDto.getTimeType();
//        float timeAmount = Float.valueOf(exerciseDto.getTimeAmount());
//        float hoursInMinute;
//
//        if (timeType == TimeType.HOURS) {
//            hoursInMinute = timeAmount * 60;
//            ratio = hoursInMinute / exerciseDetails.getTimeAmount();
//            burnCalories = ratio * exerciseDetails.getBurnCalories();
//            exercise.setBurnCalories(burnCalories);
//        } else if (timeType == TimeType.MINUTE) {
//            ratio = timeAmount / exerciseDetails.getTimeAmount();
//            burnCalories = ratio * exerciseDetails.getBurnCalories();
//            exercise.setBurnCalories(burnCalories);
//        }
//
//        exercise.setDate(currentDate);
//        exercise.setName(exerciseDetails.getName());
//
//        // Save the exercise first
//        exercise = exerciseRepository.save(exercise);
//
//        // Add the saved exercise to the user's exercise list
//        user.getExerciseList().add(exercise);
//
//        // Update the user entity
//        userRepository.save(user);
//
//        return modelMapper.map(exercise, ExerciseResponse.class);
//    }

    @Override
    public ExerciseResponse createExerciseForUser(Long userId, ExerciseDto exerciseDto) throws UserNotFoundException, ExerciseDetailsNotFoundException {

        LocalDate currentDate = LocalDate.now();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found in the database: " + userId)
        );

        ExerciseDetails exerciseDetails = exerciseDetailsRepository.findById(exerciseDto.getExerciseId()).orElseThrow(
                () -> new ExerciseDetailsNotFoundException("Exercise not found in the database")
        );

        Exercise exercise = new Exercise();
        exercise.setDate(currentDate);
        exercise.setName(exerciseDetails.getName());
        exercise.setTimeType(TimeType.valueOf(String.valueOf(exerciseDto.getTimeType())));
        exercise.setTimeAmount(exerciseDto.getTimeAmount());



        float ratio;
        float burnCalories;
        float timeAmount = exerciseDto.getTimeAmount();
        float calories = exerciseDetails.getBurnCalories();

        if (exercise.getTimeType() == TimeType.MINUTE) {
            ratio = timeAmount / exerciseDetails.getTimeAmount();
            burnCalories = calories * ratio;
        } else if (exercise.getTimeType() == TimeType.HOURS) {
            float hoursInMinutes = timeAmount * 60;
            ratio = hoursInMinutes / exerciseDetails.getTimeAmount();
            burnCalories = calories * ratio;
        } else {
            throw new IllegalArgumentException("Invalid time type: " + exercise.getTimeType());
        }


        exercise.setBurnCalories(burnCalories);

        // Save the new exercise first to generate its ID
        exercise = exerciseRepository.save(exercise);

        // Associate the exercise with the user
        user.getExerciseList().add(exercise);
//        exercise.getUserList().add(user);

        // Save the user to update the relationship
        userRepository.save(user);

        return modelMapper.map(exercise, ExerciseResponse.class);
    }




}
